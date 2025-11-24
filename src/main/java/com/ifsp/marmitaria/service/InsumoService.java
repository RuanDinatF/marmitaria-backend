package com.ifsp.marmitaria.service;

import com.ifsp.marmitaria.dto.insumo.InsumoCreateDTO;
import com.ifsp.marmitaria.dto.insumo.InsumoDTO;
import com.ifsp.marmitaria.entity.Insumo;
import com.ifsp.marmitaria.entity.ItemFichaProduto;
import com.ifsp.marmitaria.entity.TipoInsumo;
import com.ifsp.marmitaria.entity.UnidadeMedida;
import com.ifsp.marmitaria.mapper.InsumoMapper;
import com.ifsp.marmitaria.repository.InsumoRepository;
import com.ifsp.marmitaria.repository.ItemFichaProdutoRepository;
import com.ifsp.marmitaria.repository.TipoInsumoRepository;
import com.ifsp.marmitaria.repository.UnidadeMedidaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsumoService {

    private static final Logger logger = LoggerFactory.getLogger(InsumoService.class);

    private final InsumoRepository insumoRepository;
    private final InsumoMapper insumoMapper;
    private final TipoInsumoRepository tipoInsumoRepository;
    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final ItemFichaProdutoRepository itemFichaProdutoRepository;

    public InsumoDTO getById(Long id) {
        Insumo insumo = insumoRepository.findById(id).orElseThrow(() -> {
            logger.warn("Insumo não encontrado com id: {}", id);
            return new EntityNotFoundException("Insumo não encontrado com id: " + id);
        });
        return insumoMapper.toDTO(insumo);
    }

    public List<InsumoDTO> findAll() {
        List<Insumo> insumos = insumoRepository.findAll();
        return insumoMapper.toDTOs(insumos);
    }

    public InsumoDTO create(InsumoCreateDTO dto) {
        Insumo insumo = insumoMapper.toEntity(dto);

        TipoInsumo tipoInsumo = tipoInsumoRepository.findById(dto.getTipoInsumoId())
                .orElseThrow(() -> {
                    logger.warn("TipoInsumo não encontrado com id: {}", dto.getTipoInsumoId());
                    return new EntityNotFoundException("TipoInsumo não encontrado com id: " + dto.getTipoInsumoId());
                });
        insumo.setTipoInsumo(tipoInsumo);

        UnidadeMedida unidadeMedida = unidadeMedidaRepository.findById(dto.getUnidadeMedidaId())
                .orElseThrow(() -> {
                    logger.warn("UnidadeMedida não encontrada com id: {}", dto.getUnidadeMedidaId());
                    return new EntityNotFoundException("UnidadeMedida não encontrada com id: " + dto.getUnidadeMedidaId());
                });
        insumo.setUnidadeMedida(unidadeMedida);

        Insumo insumoSalvo = insumoRepository.save(insumo);
        logger.info("Insumo criado com sucesso, id: {}", insumoSalvo.getId());
        return insumoMapper.toDTO(insumoSalvo);
    }

    public InsumoDTO update(Long id, InsumoCreateDTO dto) {
        Insumo insumoExistente = insumoRepository.findById(id).orElseThrow(() -> {
            logger.warn("Insumo não encontrado para update com id: {}", id);
            return new EntityNotFoundException("Insumo não encontrado com id: " + id);
        });

        insumoExistente.setNome(dto.getNome());
        insumoExistente.setQuantidadeEstoqueIn(dto.getQuantidadeEstoque());
        insumoExistente.setCustoUnitario(dto.getCustoUnitario());
        insumoExistente.setDataValidade(dto.getDataValidade());

        TipoInsumo tipoInsumo = tipoInsumoRepository.findById(dto.getTipoInsumoId())
                .orElseThrow(() -> {
                    logger.warn("TipoInsumo não encontrado para update com id: {}", dto.getTipoInsumoId());
                    return new EntityNotFoundException("TipoInsumo não encontrado com id: " + dto.getTipoInsumoId());
                });
        insumoExistente.setTipoInsumo(tipoInsumo);

        UnidadeMedida unidadeMedida = unidadeMedidaRepository.findById(dto.getUnidadeMedidaId())
                .orElseThrow(() -> {
                    logger.warn("UnidadeMedida não encontrada para update com id: {}", dto.getUnidadeMedidaId());
                    return new EntityNotFoundException("UnidadeMedida não encontrada com id: " + dto.getUnidadeMedidaId());
                });
        insumoExistente.setUnidadeMedida(unidadeMedida);

        Insumo insumoAtualizado = insumoRepository.save(insumoExistente);
        logger.info("Insumo atualizado com sucesso, id: {}", insumoAtualizado.getId());

        return insumoMapper.toDTO(insumoAtualizado);
    }

    @Transactional
    public void delete(Long id) {
        Insumo insumoExistente = insumoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Tentativa de deletar insumo não existente, id: {}", id);
                    return new EntityNotFoundException("Insumo não encontrado para exclusão com id: " + id);
                });

        // Verifica se o insumo está sendo usado em fichas técnicas
        List<ItemFichaProduto> itensFicha = itemFichaProdutoRepository.findByInsumoId(id);
        
        if (!itensFicha.isEmpty()) {
            logger.warn("Insumo id: {} está sendo usado em {} fichas técnicas", id, itensFicha.size());
            // Retorna informação sobre quantas fichas técnicas usam este insumo
            throw new IllegalStateException(
                String.format("Este insumo está sendo utilizado em %d ficha(s) técnica(s). " +
                    "Ao confirmar a exclusão, o insumo será removido de todas as fichas técnicas.", 
                    itensFicha.size())
            );
        }

        insumoRepository.delete(insumoExistente);
        logger.info("Insumo deletado com sucesso, id: {}", id);
    }

    @Transactional
    public void deleteWithFichaTecnica(Long id) {
        Insumo insumoExistente = insumoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Tentativa de deletar insumo não existente, id: {}", id);
                    return new EntityNotFoundException("Insumo não encontrado para exclusão com id: " + id);
                });

        // Remove o insumo de todas as fichas técnicas primeiro
        List<ItemFichaProduto> itensFicha = itemFichaProdutoRepository.findByInsumoId(id);
        if (!itensFicha.isEmpty()) {
            logger.info("Removendo insumo id: {} de {} fichas técnicas", id, itensFicha.size());
            itemFichaProdutoRepository.deleteByInsumoId(id);
        }

        // Agora pode deletar o insumo
        insumoRepository.delete(insumoExistente);
        logger.info("Insumo deletado com sucesso (com fichas técnicas), id: {}", id);
    }
}
