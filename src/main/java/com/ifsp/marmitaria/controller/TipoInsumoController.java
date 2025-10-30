package com.ifsp.marmitaria.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.marmitaria.dto.insumo.TipoInsumoCreateDTO;
import com.ifsp.marmitaria.dto.insumo.TipoInsumoDTO;
import com.ifsp.marmitaria.service.TipoInsumoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tipos-insumo")
public class TipoInsumoController {

	private final TipoInsumoService tipoInsumoService;

	@GetMapping
	public ResponseEntity<List<TipoInsumoDTO>> findAll() {
	    List<TipoInsumoDTO> tipoInsumoList = tipoInsumoService.findAll();
	    return ResponseEntity.ok(tipoInsumoList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoInsumoDTO> findById(@PathVariable Long id) {
	    TipoInsumoDTO dto = tipoInsumoService.getById(id);
	    return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody @Valid TipoInsumoCreateDTO dto) {
	    TipoInsumoCreateDTO novoInsumo = tipoInsumoService.create(dto);
	    return ResponseEntity.ok("TipoInsumo Criado");
	}

	@PutMapping("/{id}")
	public ResponseEntity<TipoInsumoDTO> update(@PathVariable Long id, @RequestBody @Valid TipoInsumoCreateDTO dto) {
	    TipoInsumoDTO atualizado = tipoInsumoService.update(id, dto);
	    return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
	    tipoInsumoService.delete(id);
	    return ResponseEntity.ok("TipoInsumo deletado.");
	}

	
	
}
