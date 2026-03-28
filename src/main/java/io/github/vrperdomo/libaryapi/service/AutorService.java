package io.github.vrperdomo.libaryapi.service;

import io.github.vrperdomo.libaryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.vrperdomo.libaryapi.model.Autor;
import io.github.vrperdomo.libaryapi.repository.AutorRepository;
import io.github.vrperdomo.libaryapi.repository.LivroRepository;
import io.github.vrperdomo.libaryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorValidator autorValidator;
    private final LivroRepository livroRepository;

    public Autor salvar(Autor autor) {
        autorValidator.validarAutor(autor);
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterAutorPorId(UUID id) {
        return autorRepository.findById(id);
    }

    public void deletarAutor(Autor autor) {

        if (possuiLivro(autor)) {
            throw new OperacaoNaoPermitidaException("Não é perdimitido excluir um autor que possui livros cadastrados!");
        }
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisarAutores(String nome, String nacionalidade) {

        if(nome != null && nacionalidade != null) {
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if (nome != null) {
            return autorRepository.findByNome(nome);
        }

        if (nacionalidade != null) {
            return autorRepository.findByNacionalidade(nacionalidade);
        }

        return autorRepository.findAll();

    }

    public void atualizar(Autor autor) {

        if (autor.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor já esteja salvo na base");
        }

        autorValidator.validarAutor(autor);
        autorRepository.save(autor);
    }

    public boolean possuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);
    }
}
