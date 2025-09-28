package quantum_code.petsaber.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import quantum_code.petsaber.enuns.Role;

@Entity
@Table(name = "TB_TUTOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tutor implements Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTutor;
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    @Override
    public Long getId() {
        return idTutor;
    }

    @Override
    public Role getRole() {
        return Role.TUTOR;
    }
}
