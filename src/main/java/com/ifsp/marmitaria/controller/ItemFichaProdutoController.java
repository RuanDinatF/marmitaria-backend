package com.ifsp.marmitaria.controller;

import com.ifsp.marmitaria.dto.itemfichaproduto.ItemFichaProdutoCreateDTO;
import com.ifsp.marmitaria.dto.itemfichaproduto.ItemFichaProdutoDTO;
import com.ifsp.marmitaria.service.ItemFichaProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item-ficha-produto")
public class ItemFichaProdutoController {

    private final ItemFichaProdutoService service;

    @GetMapping
    public ResponseEntity<List<ItemFichaProdutoDTO>> findAll() {
        List<ItemFichaProdutoDTO> items = service.findAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemFichaProdutoDTO> findById(@PathVariable Long id) {
        ItemFichaProdutoDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid ItemFichaProdutoCreateDTO dto) {
        service.create(dto);
        return ResponseEntity.ok("Item Ficha Produto criado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemFichaProdutoDTO> update(@PathVariable Long id, @RequestBody @Valid ItemFichaProdutoCreateDTO dto) {
        ItemFichaProdutoDTO atualizado = service.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Item Ficha Produto deletado");
    }
}
