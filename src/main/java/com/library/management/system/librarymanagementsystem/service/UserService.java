package com.library.management.system.librarymanagementsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.library.management.system.librarymanagementsystem.model.UserModel;

import org.springframework.stereotype.Service;
@Service
public interface UserService {
    public HashMap<String, Boolean> addUser(HashMap<String,String> user);
    public List<UserModel> getAllUser();
    public Optional<UserModel> getUserById(long user_id);
    public HashMap<String, Boolean> updateUser(String user_name,String  user_phone,String admin_id,Long  user_id);
    public HashMap<String, Boolean> deleteUser(long user_id);
    public Optional<UserModel> searchUser(String user_name);
    public HashMap<String, Boolean> changeUserPassword (String user_password,long user_id);
    public UserModel loginUser(long user_phone,String user_password);
    public HashMap<String, Boolean> checkUserExits(long user_phone);
}
