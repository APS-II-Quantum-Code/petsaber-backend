package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import quantum_code.petsaber.domain.Exercicio;
import quantum_code.petsaber.dto.ExercicioRequestDto;
import quantum_code.petsaber.dto.ExercicioResponseDto;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ExericicioMapper {

    Exercicio toEntity(ExercicioRequestDto exercicioRequestDto);

    @Mapping(source = "pontuacao", target = "pontuacao")
    ExercicioResponseDto toDto(Exercicio exercicio);

    List<ExercicioResponseDto> toDto(List<Exercicio> exercicios);
}
