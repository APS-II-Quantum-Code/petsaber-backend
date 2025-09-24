package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_CONSULTOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConsultor;
    private String nome;
    private String email;
    private String senha;
}
