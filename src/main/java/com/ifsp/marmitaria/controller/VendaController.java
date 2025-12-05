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

import com.ifsp.marmitaria.dto.venda.VendaCreateDTO;
import com.ifsp.marmitaria.dto.venda.VendaDTO;
import com.ifsp.marmitaria.service.VendaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vendas")
public class VendaController {

	private final VendaService vendaService;


    @GetMapping
    public ResponseEntity<List<VendaDTO>> findAll() {
        List<VendaDTO> vendas = vendaService.findAll();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> findById(@PathVariable Long id) {
        VendaDTO dto = vendaService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid VendaCreateDTO dto) {
    	vendaService.create(dto);
        return ResponseEntity.ok("Venda criado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaDTO> update(@PathVariable Long id, @RequestBody @Valid VendaCreateDTO dto) {
        VendaDTO atualizado = vendaService.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        vendaService.delete(id);
        return ResponseEntity.ok("Venda deletado com sucesso.");
    }
	

}
