package io.github.vrperdomo.libaryapi.controller;

import io.github.vrperdomo.libaryapi.controller.dto.CadastroLivroDTO;
import io.github.vrperdomo.libaryapi.controller.dto.ErroRespostaDTO;
import io.github.vrperdomo.libaryapi.exceptions.RegistroDuplicadoException;
import io.github.vrperdomo.libaryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO cadastroLivroDTO){

        try {

            // mapear dto para entidade

            // enviar a entidade para o service validar e salvar na basa

            // criar url para acesso dos dados do livro

            // retornar código created com header location

            return null;

        } catch (RegistroDuplicadoException e){
            var erroDTO = ErroRespostaDTO.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }

    }
}
