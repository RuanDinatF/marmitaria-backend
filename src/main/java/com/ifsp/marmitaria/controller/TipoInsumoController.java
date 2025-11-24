package com.ifsp.marmitaria.controller;

import com.ifsp.marmitaria.dto.insumo.TipoInsumoDTO;
import com.ifsp.marmitaria.service.TipoInsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tipos-insumo")
public class TipoInsumoController {

    private final TipoInsumoService tipoInsumoService;

    @GetMapping
    public ResponseEntity<List<TipoInsumoDTO>> findAll() {
        List<TipoInsumoDTO> tipos = tipoInsumoService.findAll();
        return ResponseEntity.ok(tipos);
    }
}
