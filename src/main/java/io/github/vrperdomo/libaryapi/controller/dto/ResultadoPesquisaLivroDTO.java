package io.github.vrperdomo.libaryapi.controller.dto;

import io.github.vrperdomo.libaryapi.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultadoPesquisaLivroDTO(
        UUID id, String isbn,
        String Titulo,
        LocalDate dataPublicacao,
        GeneroLivro generoLivro,
        BigDecimal preco,
        AutorDTO autorDTO) {
}
