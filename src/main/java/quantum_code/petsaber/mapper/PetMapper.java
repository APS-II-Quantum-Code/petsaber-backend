package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import quantum_code.petsaber.domain.Pet;
import quantum_code.petsaber.dto.PetResponseDto;
import quantum_code.petsaber.mapper.PorteMapper;
import quantum_code.petsaber.mapper.RacaMapper;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Mapper(componentModel = "spring", uses = {RacaMapper.class, PorteMapper.class, EspecieMapper.class})
public interface PetMapper {

    @Mapping(source = "dataNascimento", target = "idade")
    @Mapping(source = "raca.especie", target = "especie")
    @Mapping(expression = "java(pet.getSexo().toString())", target = "sexo")
    PetResponseDto toDto(Pet pet);

    List<PetResponseDto> toDto(List<Pet> pets);

    default Integer calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            return null;
        }
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
