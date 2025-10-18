package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import quantum_code.petsaber.domain.Tutor;
import quantum_code.petsaber.dto.TutorDto;

@Mapper(componentModel = "spring")
public interface TutorMapper {
    TutorDto toDto(Tutor tutor);
}
