package com.library.management.system.librarymanagementsystem.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.management.system.librarymanagementsystem.model.IssuedBookModel;
import com.library.management.system.librarymanagementsystem.service.IssuedBookService;

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
@RequestMapping(path="/api/v1/issuedbook")
public class IssuedBookController {
    

    @Autowired
    private IssuedBookService issuedBookService;

    // get all issuedBook 
    @GetMapping("/")
    public  ResponseEntity<List<IssuedBookModel>> getIssuedBook() {
        return new ResponseEntity<>(issuedBookService.getAllIssuedBook(),HttpStatus.OK);
    }

  

    // get issued book by ids
    @GetMapping("/admin/{admin_id}")
    public  ResponseEntity<List<Map<String,String>>> getIssuedBookByAdmin(@PathVariable("admin_id") Long admin_id) {
        return new ResponseEntity<>(issuedBookService.getIssuedBookByAdmin(admin_id),HttpStatus.OK);
    }
    @GetMapping("/book/{book_id}")
    public  ResponseEntity<List<Map<String,String>>> getIssuedBookByBook(@PathVariable("book_id") Long book_id) {
        return new ResponseEntity<>(issuedBookService.getIssuedBookByBook(book_id),HttpStatus.OK);
    }
    @GetMapping("/user/{user_id}")
    public  ResponseEntity<List<Map<String,String>>> getIssuedBookByUser(@PathVariable("user_id") Long user_id)  {
        return new ResponseEntity<>(issuedBookService.getIssuedBookByUser(user_id),HttpStatus.OK);
    }


    // issua a book

    @PostMapping("/")
    public ResponseEntity<HashMap<String,Boolean>> addIssuedBook(@RequestBody HashMap<String,String> issueData){
        return new ResponseEntity<>(issuedBookService.addIssueBook(issueData),HttpStatus.CREATED);
    }

  


    // return issued book
    @PutMapping("/return")
    public ResponseEntity<HashMap<String,Boolean>> updateIssuedBook(@RequestBody HashMap<String,String> retunData){
        long book_id=Long.parseLong(retunData.get("book_id"));
        long user_id=Long.parseLong(retunData.get("user_id"));
        String return_status=retunData.get("return_status");
        return new ResponseEntity<>(issuedBookService.retunIssuedBook(user_id,return_status,book_id),HttpStatus.OK);
    }




    // Unused ###############################




      // get issuedbook by id
      @GetMapping("/{issuedBook_id}")
      public String getIssuedBookById(@PathVariable("issuedBook_id") Long issuedBook_id){
          return "Get issuedBook Id"+issuedBook_id;
      }



      // delete issued book
      @DeleteMapping("/{issuedBook_id}")
      public String deleteIssuedBook(@PathVariable("issuedBook_id") Integer issuedBook_id){
          return "delete issuedBook " +issuedBook_id;
      }
  
  
      // update issued book
      @PutMapping("/{issuedBook_id}")
      public String updateIssuedBook(@PathVariable("issuedBook_id") Integer issuedBook_id){
          return "update issuedBook " +issuedBook_id;
      }
}
