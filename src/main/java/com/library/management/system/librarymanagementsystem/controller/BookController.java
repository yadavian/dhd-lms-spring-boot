package com.library.management.system.librarymanagementsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.library.management.system.librarymanagementsystem.model.BookModel;
import com.library.management.system.librarymanagementsystem.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    // get all books
    @GetMapping("/")
    public ResponseEntity<List<BookModel>> getBook() {
        return  new ResponseEntity<>(bookService.getAllBook(),HttpStatus.OK);
    }

    // get book by id
    @GetMapping("/{book_id}")
    public ResponseEntity<Optional<BookModel>> getBookById(@PathVariable("book_id") Long book_id) {
        return  new ResponseEntity<>(bookService.getBookById(book_id),HttpStatus.OK);
    }


    //search book by name
    @GetMapping("/search/{book_name}")
    public ResponseEntity<Optional<BookModel>> searchBook(@PathVariable("book_name") String book_name) {
        return  new ResponseEntity<>(bookService.searchBookName(book_name),HttpStatus.OK);
    }

    // checking book is exits or not
    @GetMapping("/exits/{book_name}")
    public ResponseEntity<HashMap<String,Boolean>> checkBookIsExits(@PathVariable("book_name") String book_name) {
        return  new ResponseEntity<>(bookService.checkBookIsExits(book_name),HttpStatus.OK);
    }

    //add book
    @PostMapping("/")
    public ResponseEntity<HashMap<String,Boolean>> addBook(@RequestBody HashMap<String, String> book) {
        return  new ResponseEntity<>(bookService.addBook(book),HttpStatus.CREATED);
    }

    //delete book
    @DeleteMapping("/{book_id}")
    public ResponseEntity<HashMap<String,Boolean>> deleteBook(@PathVariable("book_id") Long book_id) {
        return  new ResponseEntity<>(bookService.deleteBook(book_id),HttpStatus.OK);
    }

    //update book
    @PutMapping("/{book_id}")
    public ResponseEntity<HashMap<String,Boolean>> updateBook(@PathVariable("book_id") Long book_id, @RequestBody HashMap<String, String> bookData) {
        return  new ResponseEntity<>(bookService.updateBook(bookData.get("book_name"), bookData.get("book_isbn"), bookData.get("book_author"),
                bookData.get("genre_type"), Long.parseLong(bookData.get("book_quantity")), Long.parseLong(bookData.get("admin_id")), book_id),HttpStatus.OK);
    }

     //update book
     @PutMapping("/quantity")
     public ResponseEntity<HashMap<String,Boolean>> updateBookQuantity(@RequestBody HashMap<String, String> bookData) {
         System.out.println(bookData);
         return  new ResponseEntity<>(bookService.updateBookQuantity(bookData.get("book_name"), Long.parseLong(bookData.get("book_quantity"))),HttpStatus.OK);
     }
 

    
}
