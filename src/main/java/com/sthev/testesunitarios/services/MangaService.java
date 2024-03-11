package com.sthev.testesunitarios.services;

import com.sthev.testesunitarios.domain.Manga;
import com.sthev.testesunitarios.domain.MangaRequest;
import com.sthev.testesunitarios.repositories.MangaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MangaService {
    @Autowired
    private MangaRepository repository;

    public List<Manga> listAll (){
        return this.repository.findAll();
    }

    public Page<Manga> pageManga(Pageable pageable){
        return this.repository.findAll(pageable);
    }

    public Manga findById(Long id){
        return this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Manga Not Found"));
    }

    public Manga save(MangaRequest dto){
        return this.repository.save(new Manga(dto));
    }

    public void update(Manga data){
        Optional<Manga> optionalManga = this.repository.findById(data.getId());
        if(optionalManga.isPresent()){
            Manga mangaSaved = optionalManga.get();
            mangaSaved.setName(data.getName());
            mangaSaved.setChapter(data.getChapter());
            this.repository.save(mangaSaved);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Manga not found");
        }
    }

    public Optional<Manga> findByName(String name){
        Optional<Manga> mangaOptional = this.repository.findMangaByName(name);
        if(mangaOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manga Not Found");
        }
        return mangaOptional;
    }

    public void delete(Long id){
        Optional<Manga> optionalManga = this.repository.findById(id);
        if(optionalManga.isPresent()){
            this.repository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manga Not Found");
        }
    }
}
