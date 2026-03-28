package io.github.vrperdomo.libaryapi.controller.dto;

import io.github.vrperdomo.libaryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 3, max = 100, message = "Campo fora do tamanho padrão")
        String nome,

        @NotNull(message = "Campo obrigatório")
        @Past(message = "A data de nascimento deve ser uma data anterior à data atual")
        LocalDate dataNascimento,

        @Size(min = 3, max = 50, message = "Campo fora do tamanho padrão")
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
