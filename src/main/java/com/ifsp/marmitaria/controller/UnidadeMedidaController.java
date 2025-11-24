package com.ifsp.marmitaria.controller;

import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaDTO;
import com.ifsp.marmitaria.service.UnidadeMedidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unidades-medida")
public class UnidadeMedidaController {

    private final UnidadeMedidaService unidadeMedidaService;

    @GetMapping
    public ResponseEntity<List<UnidadeMedidaDTO>> findAll() {
        List<UnidadeMedidaDTO> unidades = unidadeMedidaService.findAll();
        return ResponseEntity.ok(unidades);
    }
}
