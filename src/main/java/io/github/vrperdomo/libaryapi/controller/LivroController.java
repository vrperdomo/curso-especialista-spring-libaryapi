package io.github.vrperdomo.libaryapi.controller;

import io.github.vrperdomo.libaryapi.controller.dto.CadastroLivroDTO;
import io.github.vrperdomo.libaryapi.controller.dto.ErroRespostaDTO;
import io.github.vrperdomo.libaryapi.controller.mappers.LivroMapper;
import io.github.vrperdomo.libaryapi.exceptions.RegistroDuplicadoException;
import io.github.vrperdomo.libaryapi.model.Livro;
import io.github.vrperdomo.libaryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO cadastroLivroDTO) {

        // mapear dto para entidade
        Livro livro = livroMapper.toEntity(cadastroLivroDTO);

        // enviar a entidade para o service validar e salvar na basa
        livroService.salvar(livro);

        // criar url para acesso dos dados do livro
        URI location = gerarHeaderLocation(livro.getId());

        // retornar código created com header location
        return ResponseEntity.created(location).build();
    }
}
