package com.sthev.testesunitarios.controllers;
import com.sthev.testesunitarios.domain.Manga;
import com.sthev.testesunitarios.domain.MangaRequest;
import com.sthev.testesunitarios.services.MangaService;
import com.sthev.testesunitarios.util.MangaCreator;
import com.sthev.testesunitarios.util.MangaRequestCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class MangaControllerTest {
    @InjectMocks
    private MangaController mangaController;

    @Mock
    private MangaService mangaServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Manga> mangaPage = new PageImpl<>(List.of(MangaCreator.createValidManga()));
        BDDMockito.when(mangaServiceMock.pageManga(ArgumentMatchers.any()))
                .thenReturn(mangaPage);

        BDDMockito.when(mangaServiceMock.listAll())
                .thenReturn(List.of(MangaCreator.createValidManga()));

        BDDMockito.when(mangaServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(MangaCreator.createValidManga());

        BDDMockito.when(mangaServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.ofNullable(MangaCreator.createValidManga()));

        BDDMockito.when(mangaServiceMock.save(ArgumentMatchers.any(MangaRequest.class)))
                .thenReturn(MangaCreator.createValidManga());

        BDDMockito.doNothing().when(mangaServiceMock).update(ArgumentMatchers.any(Manga.class));

        BDDMockito.doNothing().when(mangaServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("List returns list of Manga when successful")
    void list_ReturnListOfMangaWhenSuccessful() {
        String expectedName = MangaCreator.createValidManga().getName();
        Integer expectedChapter = MangaCreator.createValidManga().getChapter();

        List<Manga> mangaList = mangaController.listAll().getBody();

        Assertions.assertThat(mangaList).isNotNull().isNotEmpty().hasSize(1);

        Assertions.assertThat(mangaList.get(0).getName()).isEqualTo(expectedName);
        Assertions.assertThat(mangaList.get(0).getChapter()).isEqualTo(expectedChapter);

    }

    @Test
    @DisplayName("List returns list of Manga inside page object when successful")
    void list_ReturnListOfMangaInsidePageObject_WhenSuccessful(){
        String expectedName = MangaCreator.createValidManga().getName();
        Integer expectedChapter = MangaCreator.createValidManga().getChapter();

        Page<Manga> mangaPage = mangaController.pageManga(null).getBody();

        Assertions.assertThat(mangaPage).isNotNull();

        Assertions.assertThat(mangaPage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(mangaPage.toList().get(0).getName()).isEqualTo(expectedName);
        Assertions.assertThat(mangaPage.toList().get(0).getChapter()).isEqualTo(expectedChapter);

    }

    @Test
    @DisplayName("findById returns Manga when successful")
    void findById_ReturnsManga_WhenSuccessful(){
        Long expectedId = MangaCreator.createValidManga().getId();

        Manga mangaById = mangaController.findById(1L).getBody();

        Assertions.assertThat(mangaById).isNotNull();
        Assertions.assertThat(mangaById.getId()).isNotNull().isEqualTo(expectedId);

    }

    @Test
    @DisplayName("findByName returns object Manga when successful")
    void findByName_ReturnObjectOfManga_WhenSuccessful() {
        String expectedName = MangaCreator.createValidManga().getName();

        Optional<Manga> mangaOptional = mangaController.findByName("manga").getBody();

        Assertions.assertThat(mangaOptional).isNotNull();
        assertTrue(mangaOptional.isPresent());
        assertEquals(expectedName, mangaOptional.get().getName());
    }

    @Test
    @DisplayName("findByName returns empty object when Manga not found")
    void findByName_ReturnEmptyObjectOfManga_WhenSuccessful() {
        BDDMockito.when(mangaServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Optional<Manga> mangaOptional = mangaController.findByName("manga").getBody();

        Assertions.assertThat(mangaOptional).isNotNull().isEmpty();
    }



    @Test
    @DisplayName("Delete removes Manga when successful")
    void delete_RemoveManga_WhenSuccessful(){
        Assertions.assertThatCode(() -> mangaController.delete(1L))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = mangaController.delete(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Save return Manga when successful")
    void save_ReturnManga_WhenSuccessful() {

        Manga mangaSave = mangaController.save(MangaRequestCreator.createMangaRequest()).getBody();
        Assertions.assertThat(mangaSave).isNotNull().isEqualTo(MangaCreator.createValidManga());

    }

    @Test
    @DisplayName("Replace update Manga when successful")
    void replace_UpdateManga_WhenSuccessful() {
        Assertions.assertThatCode(() -> mangaController.replace(MangaCreator.createValidUpdateManga()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = mangaController.replace(MangaCreator.createValidUpdateManga());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}