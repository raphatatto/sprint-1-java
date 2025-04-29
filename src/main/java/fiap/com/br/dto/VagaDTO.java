package fiap.com.br.dto;

import fiap.com.br.model.StatusVaga;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VagaDTO {
    private Long id;
    private String codigo;
    private StatusVaga status;
}
