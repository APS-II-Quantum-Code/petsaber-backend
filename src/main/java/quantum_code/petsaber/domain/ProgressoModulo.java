package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.*;
import quantum_code.petsaber.enuns.Status;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_PROGRESSO_MODULO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProgressoModulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgressoModulo;
    private LocalDateTime dataInicio;
    private LocalDateTime dateConclusao;
    private Double percentualAcerto;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "ID_PROGRESSO_TRILHA")
    private ProgressoTrilha progressoTrilha;

    @ManyToOne
    @JoinColumn(name = "ID_MODULO")
    private Modulo modulo;

    @OneToMany(mappedBy = "progressoModulo")
    @ToString.Exclude
    private List<ProgressoExercicio> progressoExercicios;
}
