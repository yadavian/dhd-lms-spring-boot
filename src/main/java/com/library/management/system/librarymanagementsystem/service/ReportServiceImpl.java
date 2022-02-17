package com.library.management.system.librarymanagementsystem.service;

import java.util.List;
import java.util.Map;

import com.library.management.system.librarymanagementsystem.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{
    

    @Autowired
    private BookRepository bookRepo;

    @Override
    public  List<Map<String,String>> countBookByGenre() {
        return bookRepo.countBookByGenre();

    }

    @Override
    public List<Map<String, String>> returnBookToday(String todayDate) {
        return bookRepo.returnBookToday(todayDate);
    }
}
