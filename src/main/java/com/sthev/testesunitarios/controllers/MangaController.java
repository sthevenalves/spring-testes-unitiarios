package com.sthev.testesunitarios.controllers;

import com.sthev.testesunitarios.domain.Manga;
import com.sthev.testesunitarios.domain.MangaRequest;
import com.sthev.testesunitarios.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manga")
public class MangaController {
    @Autowired
    private MangaService service;

    @GetMapping
    public ResponseEntity<List<Manga>> listAll(){
        return new ResponseEntity<>(this.service.listAll(), HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<Manga>> pageManga(Pageable pageable){
        return new ResponseEntity<>(this.service.pageManga(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manga> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Optional<Manga>> findByName(@RequestParam String name){
        return new ResponseEntity<>(this.service.findByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Manga> save(@RequestBody MangaRequest dto){
        return new ResponseEntity<>(this.service.save(dto), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Void> replace (@RequestBody Manga data){
        this.service.update(data);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
