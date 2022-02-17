package com.library.management.system.librarymanagementsystem.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.library.management.system.librarymanagementsystem.model.IssuedBookModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuedBookRepository extends JpaRepository<IssuedBookModel,Long>{
 
    // @Transactional
    // @Modifying
    // @Query(value = "insert into issued_book (return_date,admin_id,book_id,user_id) VALUES (?1,?2,?3,?4)", nativeQuery = true)
    // public void issuaABook(String return_date,long admin_id,long book_id,long user_id);

    // Get iisued Book by book id admin id and user id 

    @Query(value = "SELECT * FROM issued_book res where res.admin_id = ?1", nativeQuery = true)
    public List<Map<String,String>> getIssuedbookByAdmin(long admin_id);

    @Query(value = "SELECT * FROM issued_book res where res.book_id = ?1", nativeQuery = true)
    public List<Map<String,String>> getIssuedbookByBook(long book_id);

    @Query(value = "select issued_book.issuedbook_id, issued_book.issued_date,issued_book.return_date,issued_book.return_status,book.book_name,user.user_name,admin.admin_username from issued_book inner join book on issued_book.book_id = book.book_id inner join user on issued_book.user_id = user.user_id inner join admin on issued_book.admin_id = admin.admin_id where user.user_id = ?1", nativeQuery = true)
    public List<Map<String,String>> getIssuedbookByUser(long user_id);

    // update return status
    @Transactional
    @Modifying
    @Query(value = "update issued_book a set a.return_status = ?1 where a.user_id = ?2 AND book_id=?3", nativeQuery = true)
    public void returnIssuedBook(String return_status,long user_id,long book_id);


    // delete issuebook by admin user book ids

    @Transactional
    @Modifying
    @Query(value = "delete from issued_book u where u.user_id = ?1", nativeQuery = true)
    public void deleteIssuedbookByUser(long user_id);

    @Transactional
    @Modifying
    @Query(value = "delete from issued_book u where u.book_id = ?1", nativeQuery = true)
    public void deleteIssuedbookByBook(long book_id);
    
    @Transactional
    @Modifying
    @Query(value = "delete from issued_book u where u.admin_id = ?1", nativeQuery = true)
    public void deleteIssuedbookByAdmin(long admin_id);
}
