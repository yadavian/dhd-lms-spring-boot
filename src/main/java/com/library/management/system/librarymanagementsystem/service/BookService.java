package com.library.management.system.librarymanagementsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.library.management.system.librarymanagementsystem.model.BookModel;

import org.springframework.stereotype.Service;

@Service
public interface BookService {

   public HashMap<String, Boolean> addBook(HashMap<String, String> book);

   public HashMap<String, Boolean> deleteBook(Long book_id);

   public HashMap<String, Boolean> updateBook(String book_name, String book_isbn, String book_author, String genre_type,
         Long book_quantity, Long admin_id, Long book_id);

   public List<BookModel> getAllBook();

   public Optional<BookModel> getBookById(Long book_id);

   public Optional<BookModel> searchBookName(String book_name);

   public HashMap<String, Boolean> checkBookIsExits(String book_name);

   public HashMap<String, Boolean> updateBookQuantity(String book_name, long book_quantity);
}
