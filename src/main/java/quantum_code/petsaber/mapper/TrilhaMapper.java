package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import quantum_code.petsaber.domain.Trilha;
import quantum_code.petsaber.dto.TrilhaRequestDto;
import quantum_code.petsaber.dto.TrilhaResponseDto;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface TrilhaMapper {

    Trilha toEntity(TrilhaRequestDto trilhaRequestDto);

    @Mapping(source = "horasTotais", target = "horasTotais")
    @Mapping(expression = "java(trilha.getNivel().toString())", target = "nivel")
    TrilhaResponseDto toDto(Trilha trilha);

    @Mapping(source = "horasTotais", target = "horasTotais")
    List<TrilhaResponseDto> toDto(List<Trilha> trilha);
}
