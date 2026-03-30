package io.github.vrperdomo.libaryapi.controller;

import io.github.vrperdomo.libaryapi.controller.dto.AutorDTO;
import io.github.vrperdomo.libaryapi.controller.dto.ErroRespostaDTO;
import io.github.vrperdomo.libaryapi.controller.mappers.AutorMapper;
import io.github.vrperdomo.libaryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.vrperdomo.libaryapi.exceptions.RegistroDuplicadoException;
import io.github.vrperdomo.libaryapi.model.Autor;
import io.github.vrperdomo.libaryapi.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController implements GenericController {

    private final AutorService autorService;
    private final AutorMapper autorMapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid AutorDTO autorDTO) {

        Autor autor = autorMapper.toEntity(autorDTO);
        autorService.salvar(autor);

        URI location = gerarHeaderLocation(autor.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {

        var idAutor = UUID.fromString(id);

        return autorService.obterAutorPorId(idAutor)
                .map(autor -> {
                    AutorDTO dto = autorMapper.toDTO(autor);
                    return ResponseEntity.ok(dto);

                }).orElseGet(() -> ResponseEntity.notFound().build());

//        if (autorOptional.isPresent()) {
//            Autor autor = autorOptional.get();
//            AutorDTO dto = autorMapper.toDTO(autor);
//
//            return ResponseEntity.ok(dto);
//        }
//
//        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable("id") String id) {

        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterAutorPorId(idAutor);

        if (autorOptional.isEmpty())
            return ResponseEntity.notFound().build();

        autorService.deletarAutor(autorOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisarAutores(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {

        List<Autor> resultado = autorService.pesquisaByExample(nome, nacionalidade);

        // Utilizando o Mapper
        List<AutorDTO> lista = resultado
                .stream()
                .map(autorMapper::toDTO).collect(Collectors.toList());

        // FORMA SEM A UTILIZAÇÃO DE MAPPER
//        List<AutorDTO> lista = resultado
//                .stream()
//                .map(autor -> new AutorDTO(
//                        autor.getId(),
//                        autor.getNome(),
//                        autor.getDataNascimento(),
//                        autor.getNacionalidade())
//                ).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarAutor(@PathVariable("id") String id, @RequestBody @Valid AutorDTO autorDTO) {

        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterAutorPorId(idAutor);

        if (autorOptional.isEmpty())
            return ResponseEntity.notFound().build();

        var autor = autorOptional.get();
        autor.setNome(autorDTO.nome());
        autor.setDataNascimento(autorDTO.dataNascimento());
        autor.setNacionalidade(autorDTO.nacionalidade());

        autorService.atualizar(autor);

        return ResponseEntity.noContent().build();
    }

}
