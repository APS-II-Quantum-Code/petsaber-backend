package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import quantum_code.petsaber.domain.Exercicio;
import quantum_code.petsaber.domain.Modulo;
import quantum_code.petsaber.dto.ExercicioRequestDto;
import quantum_code.petsaber.dto.ExercicioResponseDto;
import quantum_code.petsaber.dto.ModuloRequestDto;
import quantum_code.petsaber.dto.ModuloResponseDto;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ModuloMapper {

    Modulo toEntity(ModuloRequestDto moduloRequestDto);

    ModuloResponseDto toDto(Modulo modulo);

    List<ModuloResponseDto> toDto(List<Modulo> modulos);
}
