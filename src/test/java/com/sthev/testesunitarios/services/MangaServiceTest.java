package com.sthev.testesunitarios.services;

import com.sthev.testesunitarios.domain.Manga;
import com.sthev.testesunitarios.repositories.MangaRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class MangaServiceTest {

    @InjectMocks
    private MangaService mangaService;

    @Mock
    private MangaRepository mangaRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Manga> mangaPage = new PageImpl<>(List.of(MangaCreator.createValidManga()));
        BDDMockito.when(mangaRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(mangaPage);

        BDDMockito.when(mangaRepositoryMock.findAll())
                .thenReturn(List.of(MangaCreator.createValidManga()));

        BDDMockito.when(mangaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(MangaCreator.createValidManga()));

        BDDMockito.when(mangaRepositoryMock.findMangaByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.ofNullable(MangaCreator.createValidManga()));

        BDDMockito.when(mangaRepositoryMock.save(ArgumentMatchers.any(Manga.class)))
                .thenReturn(MangaCreator.createValidManga());

        BDDMockito.doNothing().when(mangaRepositoryMock).delete(ArgumentMatchers.any(Manga.class));
    }

    @Test
    @DisplayName("ListAll returns list of Manga when successful")
    void listAll_ReturnListOfMangaWhenSuccessful() {
        String expectedName = MangaCreator.createValidManga().getName();
        Integer expectedChapter = MangaCreator.createValidManga().getChapter();

        List<Manga> mangaList = mangaService.listAll();

        Assertions.assertThat(mangaList).isNotNull().isNotEmpty().hasSize(1);

        Assertions.assertThat(mangaList.get(0).getName()).isEqualTo(expectedName);
        Assertions.assertThat(mangaList.get(0).getChapter()).isEqualTo(expectedChapter);

    }

    @Test
    @DisplayName("List returns list of Manga inside page object when successful")
    void list_ReturnListOfMangaInsidePageObject_WhenSuccessful(){
        String expectedName = MangaCreator.createValidManga().getName();
        Integer expectedChapter = MangaCreator.createValidManga().getChapter();

        Page<Manga> mangaPage = mangaService.pageManga(PageRequest.of(1,1));

        Assertions.assertThat(mangaPage).isNotNull();

        Assertions.assertThat(mangaPage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(mangaPage.toList().get(0).getName()).isEqualTo(expectedName);
        Assertions.assertThat(mangaPage.toList().get(0).getChapter()).isEqualTo(expectedChapter);

    }

    @Test
    @DisplayName("findById returns Manga when successful")
    void findById_ReturnsManga_WhenSuccessful(){
        Long expectedId = MangaCreator.createValidManga().getId();

        Manga mangaById = mangaService.findById(1L);

        Assertions.assertThat(mangaById).isNotNull();
        Assertions.assertThat(mangaById.getId()).isNotNull().isEqualTo(expectedId);

    }

    @Test
    @DisplayName("findByIdThrowsResponseStatusException when Manga not found")
    void findByName_ReturnEmptyObjectOfManga_WhenSuccessful() {
        BDDMockito.when(mangaRepositoryMock.findMangaByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mangaService.findByName("manga"));

    }

    @Test
    @DisplayName("findByIdThrowsResponseStatusException when Manga not found")
    void findById_ThrowsResponseStatusException_WhenMangaNotFound() {
        BDDMockito.when(mangaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mangaService.findById(1L));
    }

    @Test
    @DisplayName("findByNameThrowsResponseStatusException when Manga not found")
    void findByName_ThrowsResponseStatusException_WhenMangaNotFound() {
        BDDMockito.when(mangaRepositoryMock.findMangaByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mangaService.findByName("manga"));

    }



    @Test
    @DisplayName("Delete removes Manga when successful")
    void delete_RemoveManga_WhenSuccessful(){
        Assertions.assertThatCode(() -> mangaService.delete(1L))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("Save return Manga when successful")
    void save_ReturnManga_WhenSuccessful() {

        Manga mangaSave = mangaService.save(MangaRequestCreator.createMangaRequest());
        Assertions.assertThat(mangaSave).isNotNull().isEqualTo(MangaCreator.createValidManga());

    }

    @Test
    @DisplayName("Replace update Manga when successful")
    void replace_UpdateManga_WhenSuccessful() {
        Assertions.assertThatCode(() -> mangaService.update(MangaCreator.createValidUpdateManga()))
                .doesNotThrowAnyException();

    }
}