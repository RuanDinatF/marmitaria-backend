package com.ifsp.marmitaria.controller;
import com.ifsp.marmitaria.service.CaixaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ifsp.marmitaria.dto.caixa.CaixaResponseDTO;
import com.ifsp.marmitaria.dto.caixa.CaixaRequestDTO;
import java.util.List;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    private final CaixaService caixaService;

    public CaixaController(CaixaService caixaService) {
        this.caixaService = caixaService;
    }

    @GetMapping
    public ResponseEntity<List<CaixaResponseDTO>> listarTodos() {
        List<CaixaResponseDTO> lista = caixaService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/abrir")
    public ResponseEntity<CaixaResponseDTO> abrirCaixa(@RequestBody CaixaRequestDTO request) {
        return ResponseEntity.ok(caixaService.abrirCaixa(request.getValorInicial()));
    }

    @PostMapping("/fechar/{idCaixa}")
    public ResponseEntity<CaixaResponseDTO> fecharCaixa(
            @PathVariable Long idCaixa,
            @RequestParam Double valorFinal) {
        CaixaResponseDTO dto = caixaService.fecharCaixa(idCaixa, valorFinal);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/aberto")
    public ResponseEntity<CaixaResponseDTO> buscarCaixaAberto() {
        return caixaService.buscarCaixaAberto()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/teste")
    public String teste() {
        return "OK";
    }

}
