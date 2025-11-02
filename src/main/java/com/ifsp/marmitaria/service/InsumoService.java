package com.ifsp.marmitaria.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ifsp.marmitaria.dto.insumo.InsumoCreateDTO;
import com.ifsp.marmitaria.dto.insumo.InsumoDTO;
import com.ifsp.marmitaria.entity.Insumo;
import com.ifsp.marmitaria.entity.TipoInsumo;
import com.ifsp.marmitaria.entity.UnidadeMedida;
import com.ifsp.marmitaria.mapper.InsumoMapper;
import com.ifsp.marmitaria.repository.InsumoRepository;
import com.ifsp.marmitaria.repository.TipoInsumoRepository;
import com.ifsp.marmitaria.repository.UnidadeMedidaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InsumoService {
	private static final Logger logger = LoggerFactory.getLogger(InsumoService.class);

	private final InsumoRepository insumoRepository;
	private final InsumoMapper insumoMapper;
	private final TipoInsumoRepository tipoInsumoRepository;
	private final UnidadeMedidaRepository unidadeMedidaRepository;

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

	public InsumoCreateDTO create(InsumoCreateDTO dto) {
		Insumo insumo = insumoMapper.toEntity(dto);

		TipoInsumo tipoInsumo = tipoInsumoRepository.findById(dto.getTipoInsumoId()).orElseThrow(() -> {
			logger.warn("TipoInsumo não encontrado com id: {}", (dto.getTipoInsumoId()));
			return new EntityNotFoundException("TipoInsumo não encontrado com id: " + (dto.getTipoInsumoId()));
		});

		UnidadeMedida unidadeMedida = unidadeMedidaRepository.findById(dto.getUnidadeMedidaId()).orElseThrow(() -> {
			logger.warn("UnidadeMedida não encontrada com id: {}", dto.getUnidadeMedidaId());
			return new EntityNotFoundException("UnidadeMedida não encontrada com id: " + dto.getUnidadeMedidaId());
		});

		insumo.setTipoInsumo(tipoInsumo);
		insumo.setUnidadeMedida(unidadeMedida);

		insumoRepository.save(insumo);

		return dto;
	}

	public InsumoDTO update(Long id, InsumoCreateDTO dto) {
		Insumo insumoExistente = insumoRepository.findById(id).orElseThrow(() -> {
			logger.warn("Insumo não encontrado para update com id: {}", id);
			return new EntityNotFoundException("Insumo não encontrado com id: " + id);
		});

		insumoExistente.setNome(dto.getNome());
		insumoExistente.setQuantidadeEstoqueIn(dto.getQuantidadeEstoqueIn());
		insumoExistente.setCustoUnitario(dto.getCustoUnitario());
		insumoExistente.setDataValidade(dto.getDataValidade());

		TipoInsumo tipoInsumo = tipoInsumoRepository.findById(dto.getTipoInsumoId()).orElseThrow(() -> {
			logger.warn("TipoInsumo não encontrado para update com id: {}", dto.getTipoInsumoId());
			return new EntityNotFoundException("TipoInsumo não encontrado com id: " + dto.getTipoInsumoId());
		});

		UnidadeMedida unidadeMedida = unidadeMedidaRepository.findById(dto.getUnidadeMedidaId()).orElseThrow(() -> {
			logger.warn("UnidadeMedida não encontrada para update com id: {}", dto.getUnidadeMedidaId());
			return new EntityNotFoundException("UnidadeMedida não encontrada com id: " + dto.getUnidadeMedidaId());
		});

		insumoExistente.setTipoInsumo(tipoInsumo);
		insumoExistente.setUnidadeMedida(unidadeMedida);
		Insumo insumoAtualizado = insumoRepository.save(insumoExistente);

		return insumoMapper.toDTO(insumoAtualizado);
	}

	public void delete(Long id) {
		Insumo insumoExistente = insumoRepository.findById(id).orElseThrow(() -> {
			logger.warn("Tentativa de deletar insumo não existente, id: {}", id);
			return new EntityNotFoundException("Insumo não encontrado para exclusão com id: " + id);
		});

		insumoRepository.delete(insumoExistente);
	}

}
