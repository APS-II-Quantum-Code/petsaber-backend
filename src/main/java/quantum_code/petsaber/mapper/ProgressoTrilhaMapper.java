package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import quantum_code.petsaber.domain.ProgressoTrilha;
import quantum_code.petsaber.dto.ProgressoTrilhaDto;

@Mapper(componentModel = "spring", uses = TrilhaMapperImpl.class)
public interface ProgressoTrilhaMapper {

    @Mapping(expression = "java(progressoTrilha.getStatus().toString())", target = "status")
    ProgressoTrilhaDto toDto(ProgressoTrilha progressoTrilha);
}
