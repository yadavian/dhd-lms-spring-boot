package com.library.management.system.librarymanagementsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.management.system.librarymanagementsystem.exception.ResourceNotFoundException;
import com.library.management.system.librarymanagementsystem.model.AdminModel;
import com.library.management.system.librarymanagementsystem.model.BookModel;
import com.library.management.system.librarymanagementsystem.model.IssuedBookModel;
import com.library.management.system.librarymanagementsystem.model.UserModel;
import com.library.management.system.librarymanagementsystem.repository.AdminRepository;
import com.library.management.system.librarymanagementsystem.repository.BookRepository;
import com.library.management.system.librarymanagementsystem.repository.IssuedBookRepository;
import com.library.management.system.librarymanagementsystem.repository.UserRepository;
import com.library.management.system.librarymanagementsystem.utils.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuedBookServiceImpl implements IssuedBookService {

    private  ApiResponse apiResponse=null;

    @Autowired
    private IssuedBookRepository issueRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private UserRepository userRepo;

    public IssuedBookServiceImpl(){
        apiResponse=new ApiResponse();
    }
    

    @Override
    public HashMap<String, Boolean> addIssueBook(HashMap<String, String> issueData) {
        boolean flag = false;
        System.out.println(issueData);
        if (issueData != null) {
            AdminModel adminData = adminRepo.getById(Long.parseLong(issueData.get("admin_id")));
            if(adminData != null){
                System.out.println(adminData);
                UserModel userData = userRepo.getById(Long.parseLong(issueData.get("user_id")));
                if(userData != null){
                    System.out.println(userData);
                    BookModel bookData = bookRepo.getById(Long.parseLong(issueData.get("book_id")));
                    if(bookData != null){
                        System.out.println(bookData);
                        IssuedBookModel issuedBookModel = new IssuedBookModel();
                        issuedBookModel.setAdminModel(adminData);
                        issuedBookModel.setBookModel(bookData);
                        issuedBookModel.setUserModel(userData);
                        long quantity =bookData.getBook_quantity();
                        if(quantity > 0){
                            if (issueRepo.save(issuedBookModel) != null) {
                                long sum=quantity -1;
                                bookRepo.updateBookQuantity(sum, bookData.getBook_id());
                                flag = true;
                            }
                        }else{
                            System.out.println("Book Not Available");
                            throw new ResourceNotFoundException("Book Not Available");

                        }
                      
                    }else{
                        System.out.println("Book Not Found");
                        throw new ResourceNotFoundException("Book Not Found");

                    }
                }else{
                    System.out.println("user Not Found");
                    throw new ResourceNotFoundException("User Not Found");

                }
            }else{
                System.out.println("Admin Not Found");
                throw new ResourceNotFoundException("Admin Not Found");

            }
           
        }

        return apiResponse.addKeyValue(flag);
    }

    @Override
    public HashMap<String, Boolean> updateIssueStatus() {
        return apiResponse.addKeyValue(false);
    }

    @Override
    public List<IssuedBookModel> getAllIssuedBook() {
        return issueRepo.findAll();
    }

    @Override
    public List<Map<String,String>> getIssuedBookByAdmin(long admin_id) {
        List<Map<String, String>> issuedBookModel =issueRepo.getIssuedbookByAdmin(admin_id);
        if(issuedBookModel == null){
            System.out.println("Issued Book Not Found");
            throw new ResourceNotFoundException("Issued Book Not Found");

        }
        return issuedBookModel;
    }

    @Override
    public List<Map<String,String>> getIssuedBookByBook(long book_id) {
        List<Map<String,String>> issuedBookModel =issueRepo.getIssuedbookByBook(book_id);
        if(issuedBookModel == null){
            System.out.println("Issued Book Not Found");
            throw new ResourceNotFoundException("Issued Book Not Found");

        }
        return issuedBookModel;
    }

    @Override
    public List<Map<String,String>> getIssuedBookByUser(long user_id) {
        List<Map<String,String>> issuedBookModel =issueRepo.getIssuedbookByUser(user_id);
        if(issuedBookModel == null){
            System.out.println("Issued Book Not Found");
            throw new ResourceNotFoundException("Issued Book Not Found");

        }
        return issuedBookModel;
    }

 
    @Override
    public HashMap<String, Boolean> retunIssuedBook(long user_id, String return_status, Long book_id) {
        boolean flag =false;
        issueRepo.returnIssuedBook(return_status, user_id,book_id);
        BookModel bookData=bookRepo.getById(book_id);
        if(bookData != null){
            long quantity=bookRepo.getBookQuantity(bookData.getBook_name());
            long sum = quantity + 1;
            bookRepo.updateBookQuantity(sum, book_id);
            flag=true;
        }
        return apiResponse.addKeyValue(flag);
    }

}
