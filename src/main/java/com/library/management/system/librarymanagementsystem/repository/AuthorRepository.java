package com.library.management.system.librarymanagementsystem.repository;

import com.library.management.system.librarymanagementsystem.model.AuthorModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {

    // check admin already exits or not
    @Query(value = "SELECT * FROM author res where res.author_name = ?1", nativeQuery = true)
    public AuthorModel getAuthorData(String author_name);
}
