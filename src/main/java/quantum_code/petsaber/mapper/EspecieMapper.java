package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import quantum_code.petsaber.domain.Especie;
import quantum_code.petsaber.dto.EspecieResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EspecieMapper {

    EspecieResponseDto toDto(Especie especie);

    List<EspecieResponseDto> toDto(List<Especie> especies);
}
