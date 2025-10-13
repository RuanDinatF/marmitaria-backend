package com.ifsp.marmitaria.controller;

import com.ifsp.marmitaria.dto.ProdutoDTO;
import com.ifsp.marmitaria.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProdutoController {

    private final ProductService productService;

    public ResponseEntity<List<ProdutoDTO>> findAll(){
        List<ProdutoDTO> produtos = productService.findAll();
        return ResponseEntity.ok(produtos);


    }
}
