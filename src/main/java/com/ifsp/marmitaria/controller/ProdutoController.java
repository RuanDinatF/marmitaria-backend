package com.ifsp.marmitaria.controller;

import com.ifsp.marmitaria.dto.produto.ProdutoCreateDTO;
import com.ifsp.marmitaria.dto.produto.ProdutoDTO;
import com.ifsp.marmitaria.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProdutoController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll(){
        List<ProdutoDTO> produtos = productService.findAll();
        return ResponseEntity.ok(produtos);

    }

    @GetMapping ("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id){
        ProdutoDTO dto = productService.getById(id);
        return ResponseEntity.ok(dto);
    }


    @PostMapping()
    public ResponseEntity<String> create(@RequestBody @Valid ProdutoCreateDTO dto) {
        ProdutoCreateDTO novoProduto = productService.create(dto);
        return ResponseEntity.ok("Produto Criado");
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody @Valid ProdutoCreateDTO dto) {
        ProdutoDTO atualizado = productService.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
         productService.delete(id);
        return ResponseEntity.ok("Produto deletado.");
    }



}
