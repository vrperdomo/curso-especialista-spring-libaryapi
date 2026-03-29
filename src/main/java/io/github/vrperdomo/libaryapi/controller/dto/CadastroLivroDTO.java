package io.github.vrperdomo.libaryapi.controller.dto;

import io.github.vrperdomo.libaryapi.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(

        @ISBN
        @NotBlank(message = "Campo obrigatório")
        String isbn,

        @NotBlank(message = "Campo obrigatório")
        String titulo,

        @NotNull(message = "Campo obrigatório")
        @Past(message = "A data de nascimento deve ser uma data anterior à data atual")
        LocalDate dataPublicacao,

        GeneroLivro generoLivro,

        BigDecimal preco,

        @NotNull(message = "Campo obrigatório")
        UUID idAutor) {
}
