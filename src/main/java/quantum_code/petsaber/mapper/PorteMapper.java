package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import quantum_code.petsaber.dto.PorteResponseDto;
import quantum_code.petsaber.domain.Porte;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PorteMapper {

    PorteResponseDto toDto(Porte porte);

    List<PorteResponseDto> toDto(List<Porte> portes);
}
