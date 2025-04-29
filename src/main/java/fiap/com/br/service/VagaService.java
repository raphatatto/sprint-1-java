package fiap.com.br.service;

import fiap.com.br.dto.VagaCreateDTO;
import fiap.com.br.dto.VagaDTO;
import fiap.com.br.model.Vaga;
import fiap.com.br.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;

    public VagaDTO cadastrar(VagaCreateDTO dto) {
        Vaga vaga = new Vaga();
        vaga.setCodigo(dto.getCodigo());
        vaga.setStatus(dto.getStatus());
        return toDTO(vagaRepository.save(vaga));


    }

    public Page<VagaDTO> listar(Pageable pageable) {
        return vagaRepository.findAll(pageable).map(this::toDTO);
    }

    public VagaDTO buscarPorId(Long id) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vaga não encontrada"));
        return toDTO(vaga);
    }

    public VagaDTO atualizar(Long id, VagaCreateDTO dto) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vaga não encontrada"));

        vaga.setCodigo(dto.getCodigo());
        vaga.setStatus(dto.getStatus());
        return toDTO(vagaRepository.save(vaga));
    }

    public void deletar(Long id) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vaga não encontrada"));
        vagaRepository.delete(vaga);
    }

    private VagaDTO toDTO(Vaga vaga) {
        return new VagaDTO(vaga.getId(), vaga.getCodigo(), vaga.getStatus());
    }

}
