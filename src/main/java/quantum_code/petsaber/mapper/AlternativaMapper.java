package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import quantum_code.petsaber.domain.Alternativa;
import quantum_code.petsaber.domain.Exercicio;
import quantum_code.petsaber.dto.AlternativaRequestDto;
import quantum_code.petsaber.dto.AlternativaResponseDto;
import quantum_code.petsaber.dto.ExercicioRequestDto;
import quantum_code.petsaber.dto.ExercicioResponseDto;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AlternativaMapper {

    Alternativa toEntity(AlternativaRequestDto alternativaRequestDto);

    @Mapping(source = "correta", target = "correta")
    AlternativaResponseDto toDto(Alternativa alternativa);

    List<AlternativaResponseDto> toDto(List<Alternativa> alternativas);
}
