package fiap.com.br.controller;

import fiap.com.br.dto.MotoCreateDTO;
import fiap.com.br.dto.MotoDTO;
import fiap.com.br.service.MotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/motos")
public class MotoController {

    private final MotoService motoService;

    @PostMapping
    public ResponseEntity<MotoDTO> cadastrar(@RequestBody @Valid MotoCreateDTO dto) {
        return ResponseEntity.ok(motoService.cadastrar(dto));
    }

    @GetMapping
    public Page<MotoDTO> listar(Pageable pageable) {
        return motoService.listar(pageable);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<MotoDTO> buscarPorPlaca(@PathVariable String placa) {
        return ResponseEntity.ok(motoService.buscarPorPlaca(placa));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        motoService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
