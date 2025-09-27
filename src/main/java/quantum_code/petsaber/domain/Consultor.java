package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quantum_code.petsaber.enuns.Role;

@Entity
@Table(name = "TB_CONSULTOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultor implements Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConsultor;
    private String nome;
    private String email;
    private String senha;


    @Override
    public Long getId() {
        return idConsultor;
    }

    @Override
    public Role getRole() {
        return Role.CONSULTOR;
    }
}
