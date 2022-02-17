package com.library.management.system.librarymanagementsystem.model;
import javax.persistence.*;

@Entity
@Table(name = "genre")

public class GenreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long genre_id;
    @Column(name = "genre_type",unique = true,nullable = false)
    private String genre_type;



    public GenreModel() {
        
    }

    public GenreModel(Long genre_id, String genre_type) {
        this.genre_id = genre_id;
        this.genre_type = genre_type;
    }

    public Long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Long genre_id) {
        this.genre_id = genre_id;
    }

    public String getGenre_type() {
        return genre_type;
    }

    public void setGenre_type(String genre_type) {
        this.genre_type = genre_type;
    }

    @Override
    public String toString() {
        return "GenreModel [genre_id=" + genre_id + ", genre_type=" + genre_type + "]";
    }

 
}
