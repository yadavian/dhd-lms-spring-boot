package com.library.management.system.librarymanagementsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.library.management.system.librarymanagementsystem.model.AdminModel;
import com.library.management.system.librarymanagementsystem.service.AdminService;

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
@RequestMapping(path = "/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Get All admin
    @GetMapping("/")
    public ResponseEntity<List<AdminModel>> getAdmin() {
        return new ResponseEntity<>(adminService.getAllAdmin(),HttpStatus.OK);
    }

    // get admin by id
    @GetMapping("/{admin_id}")
    public ResponseEntity<Optional<AdminModel>> getAdminById(@PathVariable("admin_id") Long admin_id) {
        return new ResponseEntity<>(adminService.getAdminById(admin_id),HttpStatus.OK);
    }

    // search admin by name
    @GetMapping("/search/{admin_username}")
    public ResponseEntity<Optional<AdminModel>> getAdminById(@PathVariable("admin_username") String admin_username) {
        return new ResponseEntity<>(adminService.searchAdmin(admin_username),HttpStatus.OK);
    }

    // checking admin is already exits or not
    @GetMapping("/exits/{admin_username}")
    public  ResponseEntity<HashMap<String,Boolean>> checkAdminIsExits(@PathVariable("admin_username") String admin_username) {
        return new ResponseEntity<>(adminService.checkAdminIsExits(admin_username),HttpStatus.OK);
    }

    // register admin
    @PostMapping("/")
    public  ResponseEntity<HashMap<String,Boolean>> addAdmin(@RequestBody HashMap<String,String> admin) {

        System.out.println(admin);
        return new ResponseEntity<>(adminService.addAdmin(admin),HttpStatus.CREATED);
    
    }

    // singup admin
    @PostMapping("/login")
    public  ResponseEntity<AdminModel> loginAdmin(@RequestBody HashMap<String,String> admin) {
        // System.out.println(admin);
        return new ResponseEntity<>(adminService.loginAdmin(admin.get("admin_username"), admin.get("admin_password")),HttpStatus.OK);
    
    }

    // delete admin
    @DeleteMapping("/{admin_id}")
    public  ResponseEntity<HashMap<String,Boolean>> deleteAdmin(@PathVariable("admin_id") Integer admin_id) {
        return new ResponseEntity<>(adminService.deleteAdmin(admin_id),HttpStatus.OK);
    }

    // update admin

    @PutMapping("/{admin_id}")
    public  ResponseEntity<HashMap<String,Boolean>> updateAdmin(@PathVariable("admin_id") Long admin_id,@RequestBody HashMap<String,String> updateData) {
        return new ResponseEntity<>(adminService.updateAdmin(updateData.get("admin_username"), updateData.get("admin_role"), admin_id),HttpStatus.OK);
    }

    // change password
    @PutMapping("/password/{admin_id}")
    public  ResponseEntity<HashMap<String,Boolean>> changeAdminPassword(@PathVariable("admin_id") Long admin_id,@RequestBody HashMap<String,String> updatepassword) {
        return new ResponseEntity<>(adminService.changeAdminPassword(updatepassword.get("admin_password"), admin_id),HttpStatus.OK);
    }

    

}
