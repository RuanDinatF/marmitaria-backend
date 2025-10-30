package com.ifsp.marmitaria.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ifsp.marmitaria.dto.insumo.TipoInsumoCreateDTO;
import com.ifsp.marmitaria.dto.insumo.TipoInsumoDTO;
import com.ifsp.marmitaria.entity.TipoInsumo;
import com.ifsp.marmitaria.mapper.TipoInsumoMapper;
import com.ifsp.marmitaria.repository.TipoInsumoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TipoInsumoService {

	private static final Logger logger = LoggerFactory.getLogger(TipoInsumoService.class);

	private final TipoInsumoRepository tipoInsumoRepository;
	private final TipoInsumoMapper tipoInsumoMapper;

	public TipoInsumoDTO getById(Long id) {
		TipoInsumo tipoInsumo = tipoInsumoRepository.findById(id).orElseThrow(() -> {
			logger.warn("TipoInsumo não encontrado com id: {}", id);
			return new EntityNotFoundException("TipoInsumo não encontrado com id: " + id);
		});
		return tipoInsumoMapper.toDTO(tipoInsumo);
	}

	public List<TipoInsumoDTO> findAll() {
		List<TipoInsumo> tipoInsumoList = tipoInsumoRepository.findAll();
		return tipoInsumoMapper.toDTOs(tipoInsumoList);
	}

	public TipoInsumoCreateDTO create(TipoInsumoCreateDTO dto) {
	    TipoInsumo tipoInsumo = tipoInsumoMapper.toEntity(dto);

	    
	    tipoInsumoRepository.save(tipoInsumo);
	    return dto;
	}

	public TipoInsumoDTO update(Long id, TipoInsumoCreateDTO dto) {
		TipoInsumo tipoInsumoExistente = tipoInsumoRepository.findById(id).orElseThrow(() -> {
			logger.warn("TipoInsumo não encontrado para update com id: {}", id);
			return new EntityNotFoundException("TipoInsumo não encontrado com id: " + id);
		});

		tipoInsumoExistente.setTipo(dto.getTipo());

		TipoInsumo tipoInsumoAtualizado = tipoInsumoRepository.save(tipoInsumoExistente);
		return tipoInsumoMapper.toDTO(tipoInsumoAtualizado);
	}

	public void delete(Long id) {
		TipoInsumo tipoInsumoExistente = tipoInsumoRepository.findById(id).orElseThrow(() -> {
			logger.warn("Tentativa de deletar TipoInsumo não existente, id: {}", id);
			return new EntityNotFoundException("TipoInsumo não encontrado para exclusão com id: " + id);
		});

		tipoInsumoRepository.delete(tipoInsumoExistente);
	}
}
