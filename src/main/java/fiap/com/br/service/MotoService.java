package fiap.com.br.service;

import fiap.com.br.dto.MotoCreateDTO;
import fiap.com.br.dto.MotoDTO;
import fiap.com.br.model.Moto;
import fiap.com.br.model.StatusMoto;
import fiap.com.br.model.Vaga;
import fiap.com.br.repository.MotoRepository;
import fiap.com.br.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/motos")
@RequiredArgsConstructor
public class MotoService {

    private final MotoRepository motoRepository;
    private final VagaRepository vagaRepository;

    public MotoService(MotoRepository motoRepository, VagaRepository vagaRepository) {
        this.motoRepository = motoRepository;
        this.vagaRepository = vagaRepository;
    }

    public MotoDTO cadastrar(MotoCreateDTO dto) {
        Moto moto = new Moto();
        moto.setPlaca(dto.getPlaca());
        moto.setModelo(dto.getModelo());
        moto.setStatus(StatusMoto.ALOCADA);
        moto.setUltimaMovimentacao(LocalDateTime.now());

        if (dto.getIdVaga() != null) {
            Vaga vaga = vagaRepository.findById(dto.getIdVaga())
                    .orElseThrow(() -> new EntityNotFoundException("Vaga não encontrada"));
            moto.setVaga(vaga);
        }

        return toDTO(motoRepository.save(moto));
    }

    public Page<MotoDTO> listar(Pageable pageable) {
        return motoRepository.findAll(pageable).map(this::toDTO);
    }

    public MotoDTO buscarPorPlaca(String placa) {
        Moto moto = motoRepository.findByPlaca(placa)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        return toDTO(moto);
    }

    private MotoDTO toDTO(Moto moto) {
        String vagaCodigo = moto.getVaga() != null ? moto.getVaga().getCodigo() : "Sem vaga";
        return new MotoDTO(moto.getId(), moto.getPlaca(), moto.getModelo(), moto.getStatus(), vagaCodigo);
    }

    public MotoRepository getMotoRepository() {
        return motoRepository;
    }

    public VagaRepository getVagaRepository() {
        return vagaRepository;
    }


}
