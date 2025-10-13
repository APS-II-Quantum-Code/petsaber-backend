package quantum_code.petsaber.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import quantum_code.petsaber.domain.ProgressoModulo;
import quantum_code.petsaber.dto.ProgressoModuloDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgressoModuloMapper {

    @Mapping(source = "modulo.nome", target = "nomeModulo")
    @Mapping(source = "modulo.idModulo", target = "idModulo")
    @Mapping(expression = "java(progressoModulo.getStatus().toString())", target = "status")
    ProgressoModuloDto toDto(ProgressoModulo progressoModulo);

    @Mapping(source = "modulo.nome", target = "nomeModulo")
    List<ProgressoModuloDto> toDto(List<ProgressoModulo> progressoModulos);}
