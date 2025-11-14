package com.ifsp.marmitaria.service;

import com.ifsp.marmitaria.dto.caixa.CaixaResponseDTO;
import com.ifsp.marmitaria.entity.Caixa;
import com.ifsp.marmitaria.entity.StatusCaixa;
import com.ifsp.marmitaria.mapper.CaixaMapper;
import com.ifsp.marmitaria.repository.CaixaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CaixaService {

    private final CaixaRepository caixaRepository;

    public CaixaService(CaixaRepository caixaRepository) {
        this.caixaRepository = caixaRepository;
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
            caixa.setSaldoInicial(BigDecimal.valueOf(valorInicial));
            caixa.setSaldoFinal(BigDecimal.ZERO);

            return CaixaMapper.toDTO(caixaRepository.save(caixa));
        }
    }



    @Transactional
    public CaixaResponseDTO fecharCaixa(Long idCaixa, Double valorFinal) {
        Caixa caixa = caixaRepository.findById(idCaixa)
                .orElseThrow(() -> new IllegalArgumentException("Caixa não encontrado"));

        if (caixa.getStatus() == StatusCaixa.FECHADO) {
            throw new IllegalStateException("Este caixa já está fechado!");
        }

        caixa.setStatus(StatusCaixa.FECHADO);
        caixa.setDataFechamento(LocalDateTime.now());
        caixa.setSaldoFinal(BigDecimal.valueOf(valorFinal));

        return toDTO(caixaRepository.save(caixa));
    }

    public Optional<CaixaResponseDTO> buscarCaixaAberto() {
        return caixaRepository.findByStatus(StatusCaixa.ABERTO)
                .map(this::toDTO);
    }

    public List<CaixaResponseDTO> listarTodos() {
        return caixaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private CaixaResponseDTO toDTO(Caixa caixa) {
        return new CaixaResponseDTO(
                caixa.getId(),
                caixa.getSaldoInicial() != null ? caixa.getSaldoInicial().doubleValue() : 0.0,
                caixa.getSaldoFinal() != null ? caixa.getSaldoFinal().doubleValue() : 0.0,
                caixa.getDataAbertura(),
                caixa.getDataFechamento(),
                caixa.getStatus()
        );
    }
}
