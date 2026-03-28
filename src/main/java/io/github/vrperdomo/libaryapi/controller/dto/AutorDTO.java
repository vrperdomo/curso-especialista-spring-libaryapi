package io.github.vrperdomo.libaryapi.controller.dto;

import io.github.vrperdomo.libaryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,

        @NotBlank(message = "Campo obrigatório")
        String nome,

        @NotNull(message = "Campo obrigatório")
        LocalDate dataNascimento,

        @NotBlank(message = "Campo obrigatório")
        String nacionalidade) {

    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
