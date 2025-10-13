package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.*;
import quantum_code.petsaber.enuns.Status;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_PROGRESSO_TRILHA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressoTrilha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgressoTrilha;
    private LocalDateTime dataInicio;
    private LocalDateTime dataConclusao;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer modulosConcluidos;

    @ManyToOne
    @JoinColumn(name = "ID_TUTOR")
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "ID_TRILHA")
    private Trilha trilha;

    @OneToMany(mappedBy = "progressoTrilha")
    @ToString.Exclude
    private List<ProgressoModulo> progressoModulos;
}
