package com.ifsp.marmitaria.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.marmitaria.dto.UnidadeMedidaDTO;
import com.ifsp.marmitaria.entity.UnidadeMedida;
import com.ifsp.marmitaria.service.UnidadeMedidaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/unidades-medida")
public class UnidadeMedidaController {

	private final UnidadeMedidaService unidadeMedidaService;
	
	@GetMapping
	public ResponseEntity<List<UnidadeMedidaDTO>> findAll() {
		List<UnidadeMedidaDTO> unidadeMedidaInsumoList = unidadeMedidaService.findAll();
		return ResponseEntity.ok(unidadeMedidaInsumoList);

	}
	
	@PostMapping
    public ResponseEntity<UnidadeMedidaDTO> create(@RequestBody UnidadeMedidaDTO dto) {
        UnidadeMedida criada = unidadeMedidaService.create(dto);
        UnidadeMedidaDTO resposta = unidadeMedidaService.getById(criada.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
	    unidadeMedidaService.delete(id);
	    return ResponseEntity.noContent().build();
	}

}
