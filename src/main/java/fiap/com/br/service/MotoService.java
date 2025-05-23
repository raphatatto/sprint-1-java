package fiap.com.br.service;

import fiap.com.br.dto.MotoCreateDTO;
import fiap.com.br.dto.MotoDTO;
import fiap.com.br.exception.BusinessException;
import fiap.com.br.model.Moto;
import fiap.com.br.model.StatusMoto;
import fiap.com.br.model.StatusVaga;
import fiap.com.br.model.Vaga;
import fiap.com.br.repository.MotoRepository;
import fiap.com.br.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MotoService {

    private final MotoRepository motoRepository;
    private final VagaRepository vagaRepository;

    public MotoDTO cadastrar(MotoCreateDTO dto) {
        Moto moto = new Moto();
        moto.setPlaca(dto.getPlaca());
        moto.setModelo(dto.getModelo());
        moto.setStatus(StatusMoto.ALOCADA);
        moto.setUltimaMovimentacao(LocalDateTime.now());

        if (dto.getIdVaga() != null) {
            Vaga vaga = vagaRepository.findById(dto.getIdVaga())
                    .orElseThrow(() -> new BusinessException("Vaga não encontrada"));

            if (vaga.getStatus().equals(StatusVaga.OCUPADA)) {
                throw new BusinessException("Vaga já está ocupada");
            }


            vaga.setStatus(StatusVaga.OCUPADA);
            vagaRepository.save(vaga);

            moto.setVaga(vaga);
        }

        return toDTO(motoRepository.save(moto));
    }


    @Cacheable(value = "motosListadas", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort")
    public Page<MotoDTO> listar(Pageable pageable) {
        return motoRepository.findAll(pageable).map(this::toDTO);
    }


    @Cacheable(value = "motosPorPlaca", key = "#placa")
    public MotoDTO buscarPorPlaca(String placa) {
        Moto moto = motoRepository.findByPlaca(placa)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        return toDTO(moto);
    }


    public void remover(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));

        Vaga vaga = moto.getVaga();
        if (vaga != null) {
            vaga.setStatus(StatusVaga.LIVRE);
            vagaRepository.save(vaga);
        }

        motoRepository.delete(moto);
    }

    private MotoDTO toDTO(Moto moto) {
        String vagaCodigo = "Sem vaga";

        if (moto.getVaga() != null && moto.getVaga().getCodigo() != null) {
            vagaCodigo = moto.getVaga().getCodigo();
        }

        return new MotoDTO(
                moto.getId(),
                moto.getPlaca(),
                moto.getModelo(),
                moto.getStatus(),
                vagaCodigo
        );
    }

}
