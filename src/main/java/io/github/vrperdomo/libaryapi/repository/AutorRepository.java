package io.github.vrperdomo.libaryapi.repository;

import io.github.vrperdomo.libaryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository <Autor, UUID>{
}
