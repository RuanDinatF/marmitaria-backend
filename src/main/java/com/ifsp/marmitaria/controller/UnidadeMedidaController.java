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

import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaCreateDTO;
import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaDTO;
import com.ifsp.marmitaria.service.UnidadeMedidaService;

import jakarta.validation.Valid;
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

	@GetMapping("/{id}")
	public ResponseEntity<UnidadeMedidaDTO> getById(@PathVariable("id") Long id) {
		UnidadeMedidaDTO dto = unidadeMedidaService.getById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody @Valid UnidadeMedidaCreateDTO dto) {
	    UnidadeMedidaCreateDTO novaUnidadeMedida = unidadeMedidaService.create(dto);
	    return ResponseEntity.ok("UnidadeMedida Criado");
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<UnidadeMedidaDTO> update(@PathVariable("id") Long id, @RequestBody UnidadeMedidaCreateDTO dto) {
        UnidadeMedidaDTO atualizado = unidadeMedidaService.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		unidadeMedidaService.delete(id);
		return ResponseEntity.ok("UnidadeMedida deletado.");
	}
	
	

}
