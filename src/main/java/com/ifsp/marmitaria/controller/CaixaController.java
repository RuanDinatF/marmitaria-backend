package com.ifsp.marmitaria.controller;
import com.ifsp.marmitaria.service.CaixaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ifsp.marmitaria.dto.caixa.CaixaResponseDTO;
import com.ifsp.marmitaria.dto.caixa.CaixaRequestDTO;
import com.ifsp.marmitaria.dto.caixa.MovimentacaoCaixaCreateDTO;
import com.ifsp.marmitaria.dto.caixa.MovimentacaoCaixaDTO;
import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity<CaixaResponseDTO> buscarPorId(@PathVariable Long id) {
        return caixaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/abrir")
    public ResponseEntity<CaixaResponseDTO> abrirCaixa(@RequestBody @Valid CaixaRequestDTO request) {
        return ResponseEntity.ok(caixaService.abrirCaixa(request.getValorInicial()));
    }

    @PostMapping("/movimentacao")
    public ResponseEntity<MovimentacaoCaixaDTO> adicionarMovimentacao(
            @RequestBody @Valid MovimentacaoCaixaCreateDTO createDTO) {
        MovimentacaoCaixaDTO dto = caixaService.adicionarMovimentacao(createDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/fechar")
    public ResponseEntity<CaixaResponseDTO> fecharCaixa(@PathVariable Long id) {
        CaixaResponseDTO dto = caixaService.fecharCaixa(id, null);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCaixa(@PathVariable Long id) {
        caixaService.deletarCaixa(id);
        return ResponseEntity.noContent().build();
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
