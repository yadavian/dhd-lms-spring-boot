package com.library.management.system.librarymanagementsystem.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import com.library.management.system.librarymanagementsystem.model.BookModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

	// get data from book Name
	@Query(value = "SELECT * FROM book res where res.book_name = ?1", nativeQuery = true)
	public BookModel getBookByName(String book_name);

	// get Book quantity
	@Query(value = "SELECT book_quantity FROM book res where res.book_name = ?1", nativeQuery = true)
	public long getBookQuantity(String book_name);

	// update book quantity
	@Transactional
	@Modifying
	@Query(value = "update book a set a.book_quantity = ?1 where a.book_id = ?2", nativeQuery = true)
	public void updateBookQuantity(long book_quantity, long book_id);

	// Search A book like Operation
	@Query(value = "SELECT * FROM book res where res.book_name like ?1%", nativeQuery = true)
	public Optional<BookModel> searchBookName(String book_name);

	// Update Book
	@Transactional
	@Modifying
	@Query(value = "update book a set a.book_name = ?1,a.book_isbn = ?2,a.book_quantity = ?3 ,a.author_id = ?4,a.genre_id = ?5,a.admin_id = ?6  where a.book_id = ?7", nativeQuery = true)
	void updatebookById(String book_name, String book_isbn, Long book_quantity, Long author_id, Long genre_id,
			Long admin_id,Long book_id);

	// REPORT #########################################################

	// get no of book by genre #Report
	@Query(value = "select genre_type,CAST(count(*) AS char) as count from book inner join genre  on book.genre_id = genre.genre_id group by genre.genre_type", nativeQuery = true)
	public List<Map<String, String>> countBookByGenre();

	// get no of book by genre #Report
	@Query(value = "select book.book_name,book.book_isbn,issued_book.return_status,user.user_name, user.user_phone from issued_book inner join book on issued_book.book_id = book.book_id inner join user on issued_book.user_id = user.user_id where issued_book.return_date= ?1", nativeQuery = true)
	public List<Map<String, String>> returnBookToday(String todayDate);


	@Transactional
	@Modifying
	@Query(value = "delete from book where admin_id = ?1", nativeQuery = true)
	public void deleteBookbyAdminId(Long admin_id);

}
