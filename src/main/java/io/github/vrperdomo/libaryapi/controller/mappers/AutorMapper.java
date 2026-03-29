package io.github.vrperdomo.libaryapi.controller.mappers;

import io.github.vrperdomo.libaryapi.controller.dto.AutorDTO;
import io.github.vrperdomo.libaryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

//    source - Origem AutorDTO
//    target - Destino Autor
//    @Mapping(source = "nome", target = "nome")
    Autor toEntity(AutorDTO autorDTO);

    AutorDTO toDTO(Autor autor);
}
