package com.library.management.system.librarymanagementsystem.utils;

import java.util.HashMap;

public class ApiResponse {

    public  HashMap<String, Boolean> addKeyValue(Boolean value) {
        // Creating an empty HashMap
        HashMap<String, Boolean> hash_map = new HashMap<String, Boolean>();
        hash_map.put("success", value);
        return hash_map;
    }

}
