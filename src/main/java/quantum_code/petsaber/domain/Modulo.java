package quantum_code.petsaber.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TB_MODULO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModulo;
    private String nome;
    private String descricao;
    private Double duracaoHoras;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String conteudo;

    @OneToMany(mappedBy = "modulo")
    private List<Exercicio> exercicios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TRILHA")
    private Trilha trilha;
    
}
