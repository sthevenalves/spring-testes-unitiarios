package com.sthev.testesunitarios.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "products")
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Manga name can not be empty")
    private String name;
    private Integer chapter;

    public Manga(MangaRequest dto){
        this.name = dto.name();
        this.chapter = dto.chapter();
    }

}
