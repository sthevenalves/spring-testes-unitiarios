package com.sthev.testesunitarios.domain;

import lombok.Builder;

@Builder
public record MangaRequest(String name, Integer chapter) {
}
