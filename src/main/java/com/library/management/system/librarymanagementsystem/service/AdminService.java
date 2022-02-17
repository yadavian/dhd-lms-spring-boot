package com.library.management.system.librarymanagementsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.library.management.system.librarymanagementsystem.model.AdminModel;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    public HashMap<String, Boolean> addAdmin(HashMap<String,String> admin);

    public List<AdminModel> getAllAdmin();

    public Optional<AdminModel> getAdminById(long admin_id);

    public HashMap<String, Boolean> deleteAdmin(long admin_id);

    public Optional<AdminModel> searchAdmin(String admin_username);

    public HashMap<String, Boolean> updateAdmin(String admin_username, String admin_role, long admin_id);

    public HashMap<String, Boolean> changeAdminPassword(String admin_password, long admin_id);

    public AdminModel loginAdmin(String admin_username, String admin_password);

    public HashMap<String, Boolean> checkAdminIsExits(String admin_username);

}
