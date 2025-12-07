package com.ifsp.marmitaria.service;

import com.ifsp.marmitaria.dto.caixa.CaixaResponseDTO;
import com.ifsp.marmitaria.dto.caixa.MovimentacaoCaixaCreateDTO;
import com.ifsp.marmitaria.dto.caixa.MovimentacaoCaixaDTO;
import com.ifsp.marmitaria.entity.Caixa;
import com.ifsp.marmitaria.entity.MovimentacaoCaixa;
import com.ifsp.marmitaria.entity.StatusCaixa;
import com.ifsp.marmitaria.entity.TipoMovimentacao;
import com.ifsp.marmitaria.mapper.CaixaMapper;
import com.ifsp.marmitaria.repository.CaixaRepository;
import com.ifsp.marmitaria.repository.MovimentacaoCaixaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CaixaService {

    private final CaixaRepository caixaRepository;
    private final MovimentacaoCaixaRepository movimentacaoCaixaRepository;

    public CaixaService(CaixaRepository caixaRepository, MovimentacaoCaixaRepository movimentacaoCaixaRepository) {
        this.caixaRepository = caixaRepository;
        this.movimentacaoCaixaRepository = movimentacaoCaixaRepository;
    }

    @Transactional
    public CaixaResponseDTO abrirCaixa(Double valorInicial) {
        Optional<Caixa> caixaAberto = caixaRepository.findByStatus(StatusCaixa.ABERTO);
        if (valorInicial == null || valorInicial < 0) {
            throw new IllegalArgumentException("O valor inicial não pode ser negativo.");
        } else {
            if (caixaAberto.isPresent()) {
                throw new IllegalStateException("Já existe um caixa aberto!");
            }

            Caixa caixa = new Caixa();
            caixa.setStatus(StatusCaixa.ABERTO);
            caixa.setDataAbertura(LocalDateTime.now());
            caixa.setSaldoInicial(valorInicial);
            caixa.setSaldoFinal(0.0);

            return CaixaMapper.toDTO(caixaRepository.save(caixa));
        }
    }

    @Transactional
    public MovimentacaoCaixaDTO adicionarMovimentacao(MovimentacaoCaixaCreateDTO createDTO) {
        if (createDTO.getCaixaId() == null) {
            throw new IllegalArgumentException("ID do caixa é obrigatório");
        }
        
        Caixa caixa = caixaRepository.findById(createDTO.getCaixaId())
                .orElseThrow(() -> new IllegalArgumentException("Caixa não encontrado"));

        // Validate that caixa is open (Requirement 6.4)
        if (caixa.getStatus() != StatusCaixa.ABERTO) {
            throw new IllegalStateException("Não é possível adicionar movimentação em caixa que não está aberto");
        }

        if (createDTO.getValor() == null || createDTO.getValor() <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }

        if (createDTO.getTipo() == null) {
            throw new IllegalArgumentException("Tipo de movimentação é obrigatório");
        }

        MovimentacaoCaixa movimentacao = new MovimentacaoCaixa();
        movimentacao.setCaixa(caixa);
        movimentacao.setTipo(createDTO.getTipo());
        movimentacao.setDescricao(createDTO.getDescricao());
        movimentacao.setValor(createDTO.getValor());
        movimentacao.setDataHora(LocalDateTime.now());

        MovimentacaoCaixa saved = movimentacaoCaixaRepository.save(movimentacao);
        return CaixaMapper.movimentacaoToDTO(saved);
    }

    @Transactional
    public CaixaResponseDTO fecharCaixa(Long idCaixa, Double valorFinal) {
        // Fetch caixa with movimentacoes to calculate saldo (Requirement 6.5)
        Caixa caixa = caixaRepository.findByIdWithMovimentacoes(idCaixa)
                .orElseThrow(() -> new IllegalArgumentException("Caixa não encontrado"));

        if (caixa.getStatus() == StatusCaixa.FECHADO) {
            throw new IllegalStateException("Este caixa já está fechado!");
        }

        // Calculate balance from movimentacoes: saldoFinal = saldoInicial + sum(ENTRADA) - sum(SAIDA)
        Double saldoCalculado = calcularSaldo(caixa);

        caixa.setStatus(StatusCaixa.FECHADO);
        caixa.setDataFechamento(LocalDateTime.now());
        caixa.setSaldoFinal(saldoCalculado);

        return CaixaMapper.toDTO(caixaRepository.save(caixa));
    }

    @Transactional(readOnly = true)
    public Optional<CaixaResponseDTO> buscarCaixaAberto() {
        return caixaRepository.findByStatus(StatusCaixa.ABERTO)
                .map(CaixaMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<CaixaResponseDTO> buscarPorId(Long id) {
        return caixaRepository.findByIdWithMovimentacoes(id)
                .map(CaixaMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public List<CaixaResponseDTO> listarTodos() {
        return caixaRepository.findAll()
                .stream()
                .map(caixa -> {
                    // Force initialization of movimentacoes within transaction
                    if (caixa.getMovimentacoes() != null) {
                        caixa.getMovimentacoes().size();
                    }
                    return CaixaMapper.toDTO(caixa);
                })
                .toList();
    }

    @Transactional
    public void deletarCaixa(Long id) {
        Caixa caixa = caixaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Caixa não encontrado"));

        if (caixa.getStatus() == StatusCaixa.ABERTO) {
            throw new IllegalStateException("Não é possível deletar um caixa aberto");
        }

        caixaRepository.delete(caixa);
    }

    private Double calcularSaldo(Caixa caixa) {
        Double saldo = caixa.getSaldoInicial();
        
        if (caixa.getMovimentacoes() != null) {
            for (MovimentacaoCaixa mov : caixa.getMovimentacoes()) {
                if (mov.getTipo() == TipoMovimentacao.ENTRADA) {
                    saldo += mov.getValor();
                } else if (mov.getTipo() == TipoMovimentacao.SAIDA) {
                    saldo -= mov.getValor();
                }
            }
        }
        
        return saldo;
    }
}
