package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import quantum_code.petsaber.domain.Recompensa;
import quantum_code.petsaber.dto.RecompensaDto;

@Mapper(componentModel = "spring")
public interface RecompensaMapper {


    RecompensaDto toDto(Recompensa recompensa);
}
