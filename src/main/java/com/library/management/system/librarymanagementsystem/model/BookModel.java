package com.library.management.system.librarymanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="book")

public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long book_id;
    @Column(name="book_name",unique = true)
    private String book_name;
    @Column(name = "book_isbn",unique = true)
    private String book_isbn;
    @Column(name = "book_quantity")
    private Long book_quantity;
   
    @ManyToOne
    @JoinColumn(name = "genre_id",nullable = false)
    private GenreModel genreModel;

    @ManyToOne
    @JoinColumn(name = "author_id",nullable = false)
    private AuthorModel authorModel;

    @ManyToOne
    @JoinColumn(name = "admin_id",nullable = false)
    private AdminModel adminModel;



    public BookModel() {

    }


  




    public BookModel(Long book_id, String book_name, String book_isbn, Long book_quantity, GenreModel genreModel,
            AuthorModel authorModel, AdminModel adminModel) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_isbn = book_isbn;
        this.book_quantity = book_quantity;
        this.genreModel = genreModel;
        this.authorModel = authorModel;
        this.adminModel = adminModel;
    }







    @Override
    public String toString() {
        return "BookModel [adminModel=" + adminModel + ", authorModel=" + authorModel + ", book_id=" + book_id
                + ", book_isbn=" + book_isbn + ", book_name=" + book_name + ", book_quantity=" + book_quantity
                + ", genreModel=" + genreModel + "]";
    }







    public AuthorModel getAuthorModel() {
        return authorModel;
    }







    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }







    public AdminModel getAdminModel() {
        return adminModel;
    }







    public void setAdminModel(AdminModel adminModel) {
        this.adminModel = adminModel;
    }







    public Long getBook_id() {
        return book_id;
    }




    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }




    public String getBook_name() {
        return book_name;
    }




    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }




    public String getBook_isbn() {
        return book_isbn;
    }




    public void setBook_isbn(String book_isbn) {
        this.book_isbn = book_isbn;
    }




    public Long getBook_quantity() {
        return book_quantity;
    }




    public void setBook_quantity(Long book_quantity) {
        this.book_quantity = book_quantity;
    }






    public GenreModel getGenreModel() {
        return genreModel;
    }




    public void setGenreModel(GenreModel genreModel) {
        this.genreModel = genreModel;
    }





    public void setBook_quantity(String string) {
    }
    

    
    
     
}
