package com.sthev.testesunitarios.repositories;

import com.sthev.testesunitarios.domain.Manga;
import com.sthev.testesunitarios.util.MangaCreator;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Manga Repository")
@Log4j2
class MangaRepositoryTest {
    @Autowired
    MangaRepository mangaRepository;

    @Test
    @DisplayName("save creates Manga when successful")
    void save_PersistManga_WhenSuccessful(){
        Manga mangaToBeSaved = MangaCreator.createMangaToBeSaved();
        Manga mangaSaved = this.mangaRepository.save(mangaToBeSaved);

        Assertions.assertThat(mangaSaved).isNotNull();
        Assertions.assertThat(mangaSaved.getId()).isNotNull();
        Assertions.assertThat(mangaSaved.getName()).isEqualTo(mangaToBeSaved.getName());
        Assertions.assertThat(mangaSaved.getChapter()).isEqualTo(mangaToBeSaved.getChapter());

    }

    @Test
    @DisplayName("save updates creates Manga when successful")
    void save_UpdateManga_WhenSuccessful(){
        Manga mangaToBeSaved = MangaCreator.createMangaToBeSaved();
        Manga mangaSaved = this.mangaRepository.save(mangaToBeSaved);

        mangaSaved.setName("Overlord");
        mangaSaved.setChapter(89);
        Manga mangaUpdated = this.mangaRepository.save(mangaSaved);

        Assertions.assertThat(mangaUpdated).isNotNull();
        Assertions.assertThat(mangaUpdated.getId()).isNotNull();
        Assertions.assertThat(mangaUpdated.getName()).isEqualTo(mangaSaved.getName());
        Assertions.assertThat(mangaUpdated.getChapter()).isEqualTo(mangaSaved.getChapter());

    }

    @Test
    @DisplayName("delete removes Manga when successful")
    void delete_RemoveManga_WhenSuccessful(){
        Manga mangaToBeSaved = MangaCreator.createMangaToBeSaved();
        Manga mangaSaved = this.mangaRepository.save(mangaToBeSaved);

        this.mangaRepository.deleteById(mangaSaved.getId());

        Optional<Manga> mangaOptional =  this.mangaRepository.findById(mangaSaved.getId());

        Assertions.assertThat(mangaOptional).isEmpty();

    }

    @Test
    @DisplayName("Find by Name return object of Manga when successful")
    void findByName_ReturnObjectOfManga_WhenSuccessful(){
        Manga mangaToBeSaved = MangaCreator.createMangaToBeSaved();
        Manga mangaSaved = this.mangaRepository.save(mangaToBeSaved);

        String name = mangaSaved.getName();

        Optional<Manga> mangas = this.mangaRepository.findMangaByName(name);

        Assertions.assertThat(mangas).isNotEmpty().contains(mangaSaved);
        Assertions.assertThat(mangas).contains(mangaSaved);

    }

    @Test
    @DisplayName("save throw ConstrainViolationException when name is Empty")
    void save_ConstrainViolationException_WhenNameIsEmpty(){
        Manga manga = new Manga();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.mangaRepository.save(manga))
                .withMessageContaining("Manga name can not be empty");

    }

}