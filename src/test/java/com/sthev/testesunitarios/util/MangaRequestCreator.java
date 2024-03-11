package com.sthev.testesunitarios.util;

import com.sthev.testesunitarios.domain.Manga;
import com.sthev.testesunitarios.domain.MangaRequest;

public class MangaRequestCreator {
    public static MangaRequest createMangaRequest(){
        return MangaRequest.builder()
                .name(MangaCreator.createMangaToBeSaved().getName())
                .chapter(MangaCreator.createMangaToBeSaved().getChapter())
                .build();
    }
}
