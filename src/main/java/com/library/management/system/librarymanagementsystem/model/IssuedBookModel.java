package com.library.management.system.librarymanagementsystem.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.library.management.system.librarymanagementsystem.utils.DateAddition;



@Entity
@Table(name="issued_book")

public class IssuedBookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issuedbook_id")
    private Long issuedbook_id;
    @Column(name = "issued_date")
    private String issued_date = DateAddition.getCurrentDate();
    @Column(name = "return_date")
    private String return_date = DateAddition.getReturnDate();
    @Column(name = "return_status")
    private String return_status = "not returned";
   
    @ManyToOne
    @JoinColumn(name = "admin_id",nullable = false)
    private AdminModel adminModel;
    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private BookModel bookModel;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserModel userModel;

    
    public IssuedBookModel() {
    }


    public IssuedBookModel(Long issuedbook_id, String issued_date, String return_date, String return_status,
        AdminModel adminModel, BookModel bookModel, UserModel userModel) {
        this.issuedbook_id = issuedbook_id;
        this.issued_date = issued_date;
        this.return_date = return_date;
        this.return_status = return_status;
        this.adminModel = adminModel;
        this.bookModel = bookModel;
        this.userModel = userModel;
    }


    public Long getIssuedbook_id() {
        return issuedbook_id;
    }


    public void setIssuedbook_id(Long issuedbook_id) {
        this.issuedbook_id = issuedbook_id;
    }


    public String getIssued_date() {
        return issued_date;
    }


    public void setIssued_date(String issued_date) {
        this.issued_date = issued_date;
    }


    public String getReturn_date() {
        return return_date;
    }


    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }


    public String getReturn_status() {
        return return_status;
    }


    public void setReturn_status(String return_status) {
        this.return_status = return_status;
    }


    public AdminModel getAdminModel() {
        return adminModel;
    }


    public void setAdminModel(AdminModel adminModel) {
        this.adminModel = adminModel;
    }


    public BookModel getBookModel() {
        return bookModel;
    }


    public void setBookModel(BookModel bookModel) {
        this.bookModel = bookModel;
    }


    public UserModel getUserModel() {
        return userModel;
    }


    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }


    @Override
    public String toString() {
        return "IssuedBookModel [adminModel=" + adminModel + ", bookModel=" + bookModel + ", issued_date=" + issued_date
                + ", issuedbook_id=" + issuedbook_id + ", return_date=" + return_date + ", return_status="
                + return_status + ", userModel=" + userModel + "]";
    }


    

    
}
