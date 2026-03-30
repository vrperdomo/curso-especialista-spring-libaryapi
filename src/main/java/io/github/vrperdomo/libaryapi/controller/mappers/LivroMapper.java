package io.github.vrperdomo.libaryapi.controller.mappers;

import io.github.vrperdomo.libaryapi.controller.dto.CadastroLivroDTO;
import io.github.vrperdomo.libaryapi.model.Livro;
import io.github.vrperdomo.libaryapi.repository.AutorRepository;
import jakarta.persistence.Table;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(cadastroLivroDTO.idAutor()).orElse(null) )")
//    @Mapping(target = "genero", source = "generoLivro")
    public abstract Livro toEntity(CadastroLivroDTO cadastroLivroDTO);
}
