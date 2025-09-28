package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quantum_code.petsaber.enuns.Sexo;

import java.time.LocalDate;

@Entity
@Table(name = "TB_PET")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPet;
    private String nome;
    private LocalDate dataNascimento;
    private String urlImagem;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "ID_RACA")
    private Raca raca;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "ID_PORTE")
    private Porte porte;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "ID_TUTOR")
    private Tutor tutor;
}
