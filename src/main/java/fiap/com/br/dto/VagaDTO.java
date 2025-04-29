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

    public VagaDTO(Long id, String codigo, StatusVaga status) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public StatusVaga getStatus() {
        return status;
    }

    public void setStatus(StatusVaga status) {
        this.status = status;
    }
}
