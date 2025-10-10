package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import quantum_code.petsaber.domain.Especie;
import quantum_code.petsaber.domain.Raca;
import quantum_code.petsaber.dto.EspecieResponseDto;
import quantum_code.petsaber.dto.RacaResponseDto;
import quantum_code.petsaber.service.RacaService;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RacaMapper {

    RacaResponseDto toDto(Raca raca);

    List<RacaResponseDto> toDto(List<Raca> racas);
}
