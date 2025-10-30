package com.ifsp.marmitaria.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaCreateDTO;
import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaDTO;
import com.ifsp.marmitaria.entity.UnidadeMedida;
import com.ifsp.marmitaria.mapper.UnidadeMedidaMapper;
import com.ifsp.marmitaria.repository.UnidadeMedidaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnidadeMedidaService {

	private static final Logger logger = LoggerFactory.getLogger(UnidadeMedidaService.class);

	private final UnidadeMedidaRepository unidadeMedidaRepository;
	private final UnidadeMedidaMapper unidadeMedidaMapper;

	public UnidadeMedidaDTO getById(Long id) {
		UnidadeMedida unidadeMedida = unidadeMedidaRepository.findById(id).orElseThrow(() -> {
			logger.warn("UnidadeMedida não encontrada com id: {}", id);
			return new EntityNotFoundException("UnidadeMedida não encontrada com id: " + id);
		});
		return unidadeMedidaMapper.toDTO(unidadeMedida);
	}

	public List<UnidadeMedidaDTO> findAll() {
		List<UnidadeMedida> unidadeMedidaList = unidadeMedidaRepository.findAll();
		return unidadeMedidaMapper.toDTOs(unidadeMedidaList);
	}


	
	public UnidadeMedidaCreateDTO create(UnidadeMedidaCreateDTO dto) {
	    UnidadeMedida unidadeMedida = unidadeMedidaMapper.toEntity(dto);
	    unidadeMedidaRepository.save(unidadeMedida);
	  
	    return dto;
	}

	public UnidadeMedidaDTO update(Long id, UnidadeMedidaCreateDTO dto) {
		UnidadeMedida unidadeMedidaExistente = unidadeMedidaRepository.findById(id).orElseThrow(() -> {
			logger.warn("UnidadeMedida não encontrada para update com id: {}", id);
			return new EntityNotFoundException("UnidadeMedida não encontrada com id: " + id);
		});

		unidadeMedidaExistente.setDescricao(dto.getDescricao());
		unidadeMedidaExistente.setAbreviacao(dto.getAbreviacao());

		UnidadeMedida unidadeMedidaAtualizado = unidadeMedidaRepository.save(unidadeMedidaExistente);
		return unidadeMedidaMapper.toDTO(unidadeMedidaAtualizado);
	}

	public void delete(Long id) {
		UnidadeMedida unidadeMedidaExistente = unidadeMedidaRepository.findById(id).orElseThrow(() -> {
			logger.warn("Tentativa de deletar UnidadeMedida não existente, id: {}", id);
			return new EntityNotFoundException("UnidadeMedida não encontrada para exclusão com id: " + id);
		});

		unidadeMedidaRepository.delete(unidadeMedidaExistente);
	}


}
