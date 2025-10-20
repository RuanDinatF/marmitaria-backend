package com.ifsp.marmitaria.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.marmitaria.dto.TipoInsumoDTO;
import com.ifsp.marmitaria.service.TipoInsumoService;

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
	
	
}
