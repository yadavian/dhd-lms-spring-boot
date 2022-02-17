package com.library.management.system.librarymanagementsystem.repository;

import com.library.management.system.librarymanagementsystem.model.GenreModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository  extends JpaRepository<GenreModel,Long> {
    
    // get gnere by genre type
    // List<GenreModel> findByGenre_type(String genre_type);
    @Query(value = "SELECT * FROM genre res where res.genre_type = ?1", nativeQuery = true)
    public GenreModel getGenreDataByType(String genre_type);
   
}
