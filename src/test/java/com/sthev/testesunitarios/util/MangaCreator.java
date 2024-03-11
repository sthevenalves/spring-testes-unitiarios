package com.sthev.testesunitarios.util;

import com.sthev.testesunitarios.domain.Manga;

public class MangaCreator {

    public static Manga createMangaToBeSaved(){
        return Manga.builder()
                .name("Bleach")
                .chapter(566)
                .build();
    }

    public static Manga createValidManga(){
        return Manga.builder()
                .id(1L)
                .name("Bleach")
                .chapter(566)
                .build();
    }

    public static Manga createValidUpdateManga(){
        return Manga.builder()
                .id(1L)
                .name("Bleach")
                .chapter(566)
                .build();
    }
}
