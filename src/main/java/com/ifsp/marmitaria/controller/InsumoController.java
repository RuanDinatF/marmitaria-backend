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

import com.ifsp.marmitaria.dto.insumo.InsumoCreateDTO;
import com.ifsp.marmitaria.dto.insumo.InsumoDTO;
import com.ifsp.marmitaria.service.InsumoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/insumos")
public class InsumoController {

	private final InsumoService insumoService;
	
	@GetMapping
    public ResponseEntity<List<InsumoDTO>> findAll() {
        List<InsumoDTO> insumos = insumoService.findAll();
        return ResponseEntity.ok(insumos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsumoDTO> findById(@PathVariable Long id) {
        InsumoDTO dto = insumoService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid InsumoCreateDTO dto) {
        InsumoCreateDTO novoInsumo = insumoService.create(dto);
        return ResponseEntity.ok("Insumo Criado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsumoDTO> update(@PathVariable Long id, @RequestBody @Valid InsumoCreateDTO dto) {
        InsumoDTO atualizado = insumoService.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        insumoService.delete(id);
        return ResponseEntity.ok("Insumo deletado.");
    }
}
