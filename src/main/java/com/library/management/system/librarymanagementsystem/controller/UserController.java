package com.library.management.system.librarymanagementsystem.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.library.management.system.librarymanagementsystem.model.UserModel;
import com.library.management.system.librarymanagementsystem.service.UserService;

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
@RequestMapping(path="/api/v1/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    // get all users
    @GetMapping("/")
    public ResponseEntity<List<UserModel>> getUser() {
        return new ResponseEntity<>( userService.getAllUser(),HttpStatus.OK);
    }

    // get user by id
    @GetMapping("/{user_id}")
    public ResponseEntity<Optional<UserModel>> getUserById(@PathVariable("user_id") Long user_id){
        return new ResponseEntity<>(userService.getUserById(user_id),HttpStatus.OK);
    }

    // search user
    @GetMapping("/search/{user_name}")
    public ResponseEntity<Optional<UserModel>> searchUserName(@PathVariable("user_name") String user_name){
        return new ResponseEntity<>(userService.searchUser(user_name),HttpStatus.OK);
    }

      // search user
      @GetMapping("/exits/{user_phone}")
      public  ResponseEntity<HashMap<String,Boolean>> searchUserName(@PathVariable("user_phone") Long user_phone){
          return new ResponseEntity<>(userService.checkUserExits(user_phone),HttpStatus.OK);
      }
  

    // add user
    @PostMapping("/")
    public  ResponseEntity<HashMap<String,Boolean>> addUser(@RequestBody HashMap<String,String>  user){
        return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
    }

    
    //user login
    @PostMapping("/login")
    public  ResponseEntity<UserModel> loginUser(@RequestBody HashMap<String,String>  user){
        return new ResponseEntity<>(userService.loginUser(Long.parseLong(user.get("user_phone")), user.get("user_password")),HttpStatus.OK);
    }

    // delete user
    @DeleteMapping("/{user_id}")
    public  ResponseEntity<HashMap<String,Boolean>> deleteUser(@PathVariable("user_id") Long user_id){
        return new ResponseEntity<>(userService.deleteUser(user_id),HttpStatus.OK);
    }

    // update user
    @PutMapping("/{user_id}")
    public  ResponseEntity<HashMap<String,Boolean>> updateUser(@PathVariable("user_id") Long user_id,@RequestBody HashMap<String,String> updateData){
        return new ResponseEntity<>(userService.updateUser(updateData.get("user_name"), updateData.get("user_phone"), updateData.get("admin_id"),  user_id),HttpStatus.OK);
    }

    // change password
    @PutMapping("/password/{user_id}")
    public  ResponseEntity<HashMap<String,Boolean>> changeUserPassword(@PathVariable("user_id") Long user_id,@RequestBody HashMap<String,String> updatePassword){
        // System.out.println(updatePassword);
        return new ResponseEntity<>(userService.changeUserPassword(updatePassword.get("user_password"), user_id),HttpStatus.OK);
    }

    
}
