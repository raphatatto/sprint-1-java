package fiap.com.br.dto;

import fiap.com.br.model.StatusVaga;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VagaCreateDTO {

    @NotBlank(message = "O código da vaga é obrigatório")
    private String codigo;

    private StatusVaga status = StatusVaga.LIVRE;

    public StatusVaga getStatus() {
        return null;
    }

    public String getCodigo() {
        return "";
    }
}
