package com.ifsp.marmitaria.controller;

import com.ifsp.marmitaria.dto.itemfichaproduto.ItemFichaProdutoCreateDTO;
import com.ifsp.marmitaria.dto.itemfichaproduto.ItemFichaProdutoDTO;
import com.ifsp.marmitaria.service.ItemFichaProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemFichaProdutoController {

    private final ItemFichaProdutoService service;

    @GetMapping("/item-ficha-produto")
    public ResponseEntity<List<ItemFichaProdutoDTO>> findAll() {
        List<ItemFichaProdutoDTO> items = service.findAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/item-ficha-produto/{id}")
    public ResponseEntity<ItemFichaProdutoDTO> findById(@PathVariable Long id) {
        ItemFichaProdutoDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/produtos/{produtoId}/itens-ficha")
    public ResponseEntity<List<ItemFichaProdutoDTO>> findByProdutoId(@PathVariable Long produtoId) {
        List<ItemFichaProdutoDTO> items = service.findByProdutoId(produtoId);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/produtos/{produtoId}/itens-ficha")
    public ResponseEntity<ItemFichaProdutoCreateDTO> create(
            @PathVariable Long produtoId,
            @RequestBody @Valid ItemFichaProdutoCreateDTO dto) {
        dto.setProdutoId(produtoId);
        ItemFichaProdutoCreateDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/itens-ficha/{id}")
    public ResponseEntity<ItemFichaProdutoDTO> update(@PathVariable Long id, @RequestBody @Valid ItemFichaProdutoCreateDTO dto) {
        ItemFichaProdutoDTO atualizado = service.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/itens-ficha/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
