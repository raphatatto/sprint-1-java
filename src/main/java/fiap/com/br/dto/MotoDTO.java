package fiap.com.br.dto;

import fiap.com.br.model.StatusMoto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotoDTO {
    private Long id;
    private String placa;
    private String modelo;
    private StatusMoto status;
    private String vaga;

    public MotoDTO(Long id, String placa, String modelo, StatusMoto status, String vagaCodigo) {
    }
}
