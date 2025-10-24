package com.ifsp.marmitaria.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ifsp.marmitaria.dto.UnidadeMedidaDTO;
import com.ifsp.marmitaria.entity.UnidadeMedida;
import com.ifsp.marmitaria.mapper.UnidadeMedidaMapper;
import com.ifsp.marmitaria.repository.UnidadeMedidaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnidadeMedidaService {

	private final UnidadeMedidaRepository unidadeMedidaRepository;
	private final UnidadeMedidaMapper unidadeMedidaMapper;

	public UnidadeMedidaDTO getById(Long id) {
		UnidadeMedida unidadeMedida = unidadeMedidaRepository.findById(id).orElseThrow();
		return unidadeMedidaMapper.toDTO(unidadeMedida);
	}

	public List<UnidadeMedidaDTO> findAll() {
		List<UnidadeMedida> unidadeMedidaList = unidadeMedidaRepository.findAll();
		return unidadeMedidaMapper.toDTOs(unidadeMedidaList);
	}

	public UnidadeMedida create(UnidadeMedidaDTO dto) {
		UnidadeMedida unidadeMedida = unidadeMedidaMapper.toEntity(dto);
		return unidadeMedidaRepository.save(unidadeMedida);
	}

	public UnidadeMedidaDTO update(Long id, UnidadeMedidaDTO dto) {
		UnidadeMedida unidadeMedidaExistente = unidadeMedidaRepository.findById(id).orElseThrow();

		unidadeMedidaExistente.setDescricao(dto.getDescricao());
		unidadeMedidaExistente.setAbreviacao(dto.getAbreviacao());

		UnidadeMedida unidadeMedidaAtualizado = unidadeMedidaRepository.save(unidadeMedidaExistente);
		return unidadeMedidaMapper.toDTO(unidadeMedidaAtualizado);

	}
	
	public void delete(Long id) {
	    if (!unidadeMedidaRepository.existsById(id)) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UnidadeMedida não encontrado");
	    }
	    unidadeMedidaRepository.deleteById(id);
	}

}
