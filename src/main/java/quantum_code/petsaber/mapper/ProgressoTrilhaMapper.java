package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import quantum_code.petsaber.domain.ProgressoTrilha;
import quantum_code.petsaber.dto.ProgressoTrilhaDto;

@Mapper(componentModel = "spring", uses = TrilhaMapperImpl.class)
public interface ProgressoTrilhaMapper {

    ProgressoTrilhaDto toDto(ProgressoTrilha progressoTrilha);
}
