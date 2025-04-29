package fiap.com.br.controller;

import fiap.com.br.dto.VagaCreateDTO;
import fiap.com.br.dto.VagaDTO;
import fiap.com.br.service.VagaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vagas")
@RequiredArgsConstructor
public class VagaController {

    private final VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @PostMapping
    public ResponseEntity<VagaDTO> cadastrar(@RequestBody @Valid VagaCreateDTO dto) {
        return ResponseEntity.ok(vagaService.cadastrar(dto));
    }

    @GetMapping
    public Page<VagaDTO> listar(Pageable pageable) {
        return vagaService.listar(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(vagaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid VagaCreateDTO dto) {
        return ResponseEntity.ok(vagaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vagaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
