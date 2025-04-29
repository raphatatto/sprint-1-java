package fiap.com.br.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotoCreateDTO {

    @NotBlank
    private String placa;

    @NotBlank
    private String modelo;

    private Long idVaga; // Opcional: pode vir null
}
