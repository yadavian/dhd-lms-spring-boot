package com.library.management.system.librarymanagementsystem.controller;

import java.util.List;
import java.util.Map;

import com.library.management.system.librarymanagementsystem.service.ReportService;
import com.library.management.system.librarymanagementsystem.utils.DateAddition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/report")
public class ReportController {
    @Autowired
    private ReportService reportService; 

    @GetMapping("/count")
    public List<Map<String,String>> getUserById(){
         return reportService.countBookByGenre();
    }


    @GetMapping("/today")
    public List<Map<String,String>> getReturnBooksToday(){
         return reportService.returnBookToday(DateAddition.getCurrentDate());
    }

}
