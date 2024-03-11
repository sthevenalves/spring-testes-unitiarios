package com.sthev.testesunitarios.repositories;

import com.sthev.testesunitarios.domain.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MangaRepository extends JpaRepository<Manga, Long> {

    Optional<Manga> findMangaByName(String name);
}
