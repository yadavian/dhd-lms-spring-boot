package com.library.management.system.librarymanagementsystem.utils;
import java.text.SimpleDateFormat;  
import java.util.Date;

public class DateAddition {

    private static Date today = new Date();
   
    private static long ltime = today.getTime() + 7 * 24 * 60 * 60 * 1000;
    private static Date today8 = new Date(ltime);
   
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
        String strDate = formatter.format(today);  
        return strDate;
    }
    
    public static String getReturnDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
        String strDate = formatter.format(today8);  
        return strDate;
    }
}