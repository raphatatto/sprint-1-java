package fiap.com.br.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String placa;

    private String modelo;

    @Enumerated(EnumType.STRING)
    private StatusMoto status;

    private LocalDateTime ultimaMovimentacao;

    @ManyToOne
    private Vaga vaga;
}
