package com.library.management.system.librarymanagementsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.library.management.system.librarymanagementsystem.exception.ResourceNotFoundException;
import com.library.management.system.librarymanagementsystem.model.AdminModel;
import com.library.management.system.librarymanagementsystem.model.AuthorModel;
import com.library.management.system.librarymanagementsystem.model.BookModel;
import com.library.management.system.librarymanagementsystem.model.GenreModel;
import com.library.management.system.librarymanagementsystem.repository.AdminRepository;
import com.library.management.system.librarymanagementsystem.repository.AuthorRepository;
import com.library.management.system.librarymanagementsystem.repository.BookRepository;
import com.library.management.system.librarymanagementsystem.repository.GenreRepository;
import com.library.management.system.librarymanagementsystem.repository.IssuedBookRepository;
import com.library.management.system.librarymanagementsystem.utils.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	private ApiResponse apiResponse = null;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private AuthorRepository authRepo;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private IssuedBookRepository issuedRepo;

	public BookServiceImpl() {
		apiResponse = new ApiResponse();
	}

	@Override
	public HashMap<String, Boolean> addBook(HashMap<String, String> book) {
		boolean flag = false;
		boolean genreFlag = false;
		boolean authorFlag = false;
		GenreModel genreData = genreRepository.getGenreDataByType(book.get("genre_type"));

		if (genreData == null) {
			GenreModel newGenre = new GenreModel();
			newGenre.setGenre_type(book.get("genre_type"));
			if (genreRepository.save(newGenre) != null) {
				genreFlag = true;
			}
		} else {
			genreFlag = true;
		}

		System.out.println(genreData);

		AuthorModel authorModel = authRepo.getAuthorData(book.get("book_author"));
		if (authorModel == null) {
			AuthorModel newAuthorModel = new AuthorModel();
			newAuthorModel.setAuthorName(book.get("book_author"));
			if (authRepo.save(newAuthorModel) != null) {
				authorFlag = true;
			}
		} else {
			authorFlag = true;
		}

		System.out.println(authorModel);

		if (genreFlag && authorFlag) { 
			GenreModel genre = genreRepository.getGenreDataByType(book.get("genre_type")); 
			AdminModel adminModel = adminRepo.getById(Long.parseLong(book.get("admin_id")));
			AuthorModel author = authRepo.getAuthorData(book.get("book_author"));
			BookModel newBook = new BookModel();
			System.out.println(book);
			
			newBook.setBook_isbn(book.get("book_isbn"));
			newBook.setBook_name(book.get("book_name"));
			newBook.setBook_quantity(Long.parseLong(book.get("book_quantity")));
			newBook.setGenreModel(genre);
			newBook.setAuthorModel(author);
			newBook.setAdminModel(adminModel);
			
			System.out.println(newBook);
			if (bookRepository.save(newBook) != null) {
				flag = true;
			}
		}

		return apiResponse.addKeyValue(flag);
	}

	@Override
	public HashMap<String, Boolean> deleteBook(Long book_id) {
		boolean flag = false;

		if (bookRepository.findById(book_id).isPresent()) {
			// first delete Constarints

			issuedRepo.deleteIssuedbookByBook(book_id);
			bookRepository.deleteById(book_id);
			if (!bookRepository.findById(book_id).isPresent()) {
				flag = true;
			}
		} else {
			System.out.println("Book Not Found");
			throw new ResourceNotFoundException("Book Not Found");
		}
		return apiResponse.addKeyValue(flag);
	}

	@Override
	public List<BookModel> getAllBook() {
		List<BookModel> getBook = bookRepository.findAll();
		return getBook;
	}

	@Override
	public Optional<BookModel> getBookById(Long book_id) {
		Optional<BookModel> getBook = bookRepository.findById(book_id);
		if (!getBook.isPresent()) {
			System.out.println("Book Not Found");
			throw new ResourceNotFoundException("Book Not Found");

		}
		return getBook;
	}

	@Override
	public Optional<BookModel> searchBookName(String book_name) {
		return bookRepository.searchBookName(book_name);
	}

	@Override
	public HashMap<String, Boolean> checkBookIsExits(String book_name) {
		boolean flag = false;
		BookModel isBookExits = bookRepository.getBookByName(book_name);
		if (isBookExits != null) {
			flag = true;
		}
		return apiResponse.addKeyValue(flag);
	}

	@Override
	public HashMap<String, Boolean> updateBook(String book_name, String book_isbn, String book_author,
			String genre_type, Long book_quantity, Long admin_id, Long book_id) {
		boolean flag = false;
		Optional<BookModel> bookData = bookRepository.findById(book_id);
		if (bookData.isPresent()) {

			GenreModel genreModel = genreRepository.getGenreDataByType(genre_type);
			AuthorModel authorModel = authRepo.getAuthorData(book_author);

			System.out.println(genreModel);
			System.out.println(authorModel);

			GenreModel newGenre = new GenreModel();
			AuthorModel newAuthor = new AuthorModel();

			if (genreModel == null) {
				System.out.println("genre null");
				newGenre.setGenre_type(genre_type);
				genreModel = newGenre;
				genreRepository.save(newGenre);
//				System.out.println(newGenre.toString());
			}

			if (authorModel == null) {
				System.out.println("author null");
				newAuthor.setAuthorName(book_author);
				authRepo.save(newAuthor);
				authorModel = newAuthor;
//				System.out.println(newAuthor.toString());
			}

			System.out.println("genre and author = null");

			System.out.println(book_name + " " + book_isbn + " " + book_quantity + " " + authorModel.getAuthorId() + " "
					+ genreModel.getGenre_id() + " " + book_id + " " + admin_id);

			bookRepository.updatebookById(book_name, book_isbn, book_quantity, authorModel.getAuthorId(),
					genreModel.getGenre_id(), admin_id, book_id);
			flag = true;

		} else {
			System.out.println("Book Not Found");
			throw new ResourceNotFoundException("Book Not Found");

		}
		return apiResponse.addKeyValue(flag);
	}

	@Override
	public HashMap<String, Boolean> updateBookQuantity(String book_name, long book_quantity) {
		boolean flag = false;
		BookModel bookData = bookRepository.getBookByName(book_name);
		// System.out.println(bookData);
		if (bookData != null) {
			if (bookData.getBook_name().equals(book_name)) {
				long quantity = bookRepository.getBookQuantity(book_name);
				// System.out.println(quantity);
				long sum = quantity + book_quantity;
				// System.out.println(bookData.getBook_id());
				// System.out.println(sum);
				bookRepository.updateBookQuantity(sum, bookData.getBook_id());
				System.out.println("Book Quantity updated");
				flag = true;
			}

		}
		return apiResponse.addKeyValue(flag);
	}

}
