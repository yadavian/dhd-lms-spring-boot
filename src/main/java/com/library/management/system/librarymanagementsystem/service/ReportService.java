package com.library.management.system.librarymanagementsystem.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ReportService {
    
    public List<Map<String,String>> countBookByGenre();
    public  List<Map<String,String>> returnBookToday(String todayDate);
}
