package com.library.management.system.librarymanagementsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.management.system.librarymanagementsystem.model.IssuedBookModel;

import org.springframework.stereotype.Service;

@Service
public interface IssuedBookService {

    public HashMap<String, Boolean> addIssueBook(HashMap<String, String> issueData);

    public HashMap<String, Boolean> updateIssueStatus();

    public List<IssuedBookModel> getAllIssuedBook();

    public List<Map<String,String>> getIssuedBookByAdmin(long admin_id);

    public List<Map<String,String>> getIssuedBookByBook(long book_id);

    public List<Map<String,String>> getIssuedBookByUser(long user_id);

    public HashMap<String, Boolean> retunIssuedBook(long user_id, String return_status, Long book_id);

}
