package quantum_code.petsaber.domain;

import quantum_code.petsaber.enuns.Role;

public interface Usuario {
    Long getId();

    String getEmail();

    String getSenha();

    Role getRole();

    String getNome();
}
