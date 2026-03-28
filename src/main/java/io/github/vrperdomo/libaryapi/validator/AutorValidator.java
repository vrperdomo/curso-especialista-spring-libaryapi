package io.github.vrperdomo.libaryapi.validator;

import io.github.vrperdomo.libaryapi.exceptions.RegistroDuplicadoException;
import io.github.vrperdomo.libaryapi.model.Autor;
import io.github.vrperdomo.libaryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository autorRepository;

    public AutorValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void validarAutor(Autor autor) {
        if (existeAutorCadastrado(autor)){
            throw new RegistroDuplicadoException("Autor já cadastrado !");
        }

    }

    private boolean existeAutorCadastrado(Autor autor) {
        Optional<Autor> autorEncontrado = autorRepository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade()
        );

        if (autor.getId() == null) {
            return autorEncontrado.isPresent();
        }

        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }
}
