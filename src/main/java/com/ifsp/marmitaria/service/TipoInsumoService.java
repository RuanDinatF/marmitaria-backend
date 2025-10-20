package com.ifsp.marmitaria.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ifsp.marmitaria.dto.TipoInsumoDTO;
import com.ifsp.marmitaria.entity.TipoInsumo;
import com.ifsp.marmitaria.mapper.TipoInsumoMapper;
import com.ifsp.marmitaria.repository.TipoInsumoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoInsumoService {

	private final TipoInsumoRepository tipoInsumoRepository;
	private final TipoInsumoMapper tipoInsumoMapper;

	public TipoInsumoDTO getById(Long id) {
		TipoInsumo tipoInsumo = tipoInsumoRepository.findById(id).orElseThrow();
		return tipoInsumoMapper.toDTO(tipoInsumo);
	}

	public List<TipoInsumoDTO> findAll() {
		List<TipoInsumo> tipoInsumoList = tipoInsumoRepository.findAll();
		return tipoInsumoMapper.toDTOs(tipoInsumoList);
	}

	public TipoInsumo create(TipoInsumoDTO dto) {
		TipoInsumo tipoInsumo = tipoInsumoMapper.toEntity(dto);
		return tipoInsumoRepository.save(tipoInsumo);
	}

	public TipoInsumoDTO update(Long id, TipoInsumoDTO dto) {
		TipoInsumo tipoInsumoExistente = tipoInsumoRepository.findById(id).orElseThrow();
		tipoInsumoExistente.setTipo(dto.getTipo());

		TipoInsumo tipoInsumoAtualizado = tipoInsumoRepository.save(tipoInsumoExistente);
		return tipoInsumoMapper.toDTO(tipoInsumoAtualizado);
	}
	
	public void delete(Long id) {
	    if (!tipoInsumoRepository.existsById(id)) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoInsumo não encontrado");
	    }
	    tipoInsumoRepository.deleteById(id);
	}

}
