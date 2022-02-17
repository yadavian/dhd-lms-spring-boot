package com.library.management.system.librarymanagementsystem.controller;
import com.library.management.system.librarymanagementsystem.model.GenreModel;
import com.library.management.system.librarymanagementsystem.service.GenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;


    @GetMapping("/{genre_id}")
    public long getGenreById(@PathVariable("genre_id") Long genre_id){
        return genreService.getGenreById(genre_id);
    }

    @PostMapping("/")
    public boolean addGenre(@RequestBody GenreModel gernr){
        return genreService.addGenre(gernr);
    }
}
