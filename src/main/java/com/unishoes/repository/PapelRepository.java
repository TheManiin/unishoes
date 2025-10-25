package com.unishoes.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.unishoes.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
    Optional<Papel> findByNome(String nome);
}
