package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "TB_RECOMPENSA_TUTOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecompensaTutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_RECOMPENSA")
    private Recompensa recompensa;

    @ManyToOne
    @JoinColumn(name = "ID_TUTOR")
    private Tutor tutor;

    private LocalDate dataResgate;
}
