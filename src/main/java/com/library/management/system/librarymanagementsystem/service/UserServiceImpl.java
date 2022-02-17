package com.library.management.system.librarymanagementsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.library.management.system.librarymanagementsystem.exception.ResourceNotFoundException;
import com.library.management.system.librarymanagementsystem.model.AdminModel;
import com.library.management.system.librarymanagementsystem.model.UserModel;
import com.library.management.system.librarymanagementsystem.repository.AdminRepository;
import com.library.management.system.librarymanagementsystem.repository.IssuedBookRepository;
import com.library.management.system.librarymanagementsystem.repository.UserRepository;
import com.library.management.system.librarymanagementsystem.utils.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private ApiResponse apiResponse = null;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private IssuedBookRepository issuedRepo;

	public UserServiceImpl() {
		apiResponse = new ApiResponse();
	}

	@Override
	public HashMap<String, Boolean> addUser(HashMap<String, String> user) {

		boolean flag = false;
		String encryptedPassword = user.get("user_password");
		// System.out.println(encryptedPassword);
		AdminModel admin = adminRepo.getById(Long.parseLong(user.get("admin_id")));
		UserModel userModel = new UserModel();
		userModel.setUser_name(user.get("user_name"));
		userModel.setUser_phone(Long.parseLong(user.get("user_phone")));
		userModel.setUser_password(encryptedPassword);
		userModel.setAdminModel(admin);

		if (userRepo.save(userModel) != null) {
			flag = true;
		}
		return apiResponse.addKeyValue(flag);

	}

	@Override
	public List<UserModel> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public Optional<UserModel> getUserById(long user_id) {
		Optional<UserModel> getUser = userRepo.findById(user_id);
		if (!getUser.isPresent()) {
			System.out.println("User Not found");
			throw new ResourceNotFoundException("User Not Found");

		}
		return getUser;
	}

	@Override
	public HashMap<String, Boolean> updateUser(String user_name, String user_phone, String admin_id, Long user_id) {
		boolean flag = false;
		Optional<UserModel> userData = userRepo.findById(user_id);
		if (userData.isPresent()) {
			userRepo.updateUserById(user_name, Long.parseLong(user_phone), Long.parseLong(admin_id), user_id);
			flag = true;
		} else {
			System.out.println("User Not Found");
			throw new ResourceNotFoundException("User Not Found");

		}
		return apiResponse.addKeyValue(flag);
	}

	@Override
	public HashMap<String, Boolean> deleteUser(long user_id) {
		boolean flag = false;

		if (userRepo.findById(user_id).isPresent()) {
			// first delete Constarints
			issuedRepo.deleteIssuedbookByUser(user_id);
			userRepo.deleteById(user_id);
			if (!userRepo.findById(user_id).isPresent()) {
				flag = true;
			}
		} else {
			System.out.println("User Not Found");
			throw new ResourceNotFoundException("User Not Found");

		}
		return apiResponse.addKeyValue(flag);
	}

	@Override
	public HashMap<String, Boolean> changeUserPassword(String user_password, long user_id) {
		boolean flag = false;
		Optional<UserModel> userData = userRepo.findById(user_id);
		if (userData.isPresent()) {
			userRepo.changeUserPassword(user_password, user_id);
			flag = true;
		} else {
			System.out.println("User Not Found");
			throw new ResourceNotFoundException("User Not Found");

		}
		return apiResponse.addKeyValue(flag);

	}

	@Override
	public Optional<UserModel> searchUser(String user_name) {
		return userRepo.searchUserName(user_name);
	}

	@Override
	public UserModel loginUser(long user_phone, String user_password) {
		UserModel userModel; 
		String encryptedPassword = userRepo.loginWithUserPhone(user_phone);

		if (encryptedPassword.equals(user_password)) { 
			userModel = userRepo.checkUserExits(user_phone);
		} else {
			System.out.println("User Phone Number And Password Invalid");
			throw new ResourceNotFoundException("User Phone Number And Password Invalid");
		}

		return userModel;
	}

	@Override
	public HashMap<String, Boolean> checkUserExits(long user_phone) {
		boolean flag = false;
		UserModel user = userRepo.checkUserExits(user_phone);
		if (user != null) {
			flag = true;
		}
		return apiResponse.addKeyValue(flag);
	}

}
