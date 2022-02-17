package com.library.management.system.librarymanagementsystem.service;

import java.util.Optional;

import com.library.management.system.librarymanagementsystem.exception.ResourceNotFoundException;
import com.library.management.system.librarymanagementsystem.model.GenreModel;
import com.library.management.system.librarymanagementsystem.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public boolean addGenre(GenreModel gernr) {
        boolean flag = false;
        if (genreRepository.save(gernr) != null) {
            flag = true;
        }
        return flag;
    }

    @Override
    public long getGenreById(Long genre_id) {
        long genreid=0;
        Optional<GenreModel> getGnere = genreRepository.findById(genre_id);
        // System.out.println(getGnere);
        if(getGnere.isPresent()){
            genreid=getGnere.get().getGenre_id();
        }else{
            System.out.println("Genre Not Found");   
            throw new ResourceNotFoundException("Genre Not Found");

        }
        // System.out.println(genreid);   
        return genreid; 
    }

   

}
