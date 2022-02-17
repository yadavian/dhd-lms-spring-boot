package com.library.management.system.librarymanagementsystem.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import com.library.management.system.librarymanagementsystem.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	// get user details by id
	@Query(value = "SELECT * FROM user res where res.user_id = ?1", nativeQuery = true)
	public UserModel getUser(Long user_id);

	// search user name usng like
	@Query(value = "SELECT * FROM user res where res.user_name like ?1%", nativeQuery = true)
	public Optional<UserModel> searchUserName(String user_name);

	// update user info
	@Transactional
	@Modifying
	@Query(value = "update user a set a.user_name = ?1,a.user_phone = ?2,a.admin_id = ?3 where a.user_id = ?4", nativeQuery = true)
	void updateUserById(String user_name, Long user_phone, Long admin_id, Long user_id);

	// change password
	@Transactional
	@Modifying
	@Query(value = "update user a set a.user_password = ?1 where a.user_id = ?2", nativeQuery = true)
	void changeUserPassword(String user_password, Long user_id);

	// user login
	@Query(value = "SELECT user_password FROM user res where res.user_phone = ?1", nativeQuery = true)
	public String loginWithUserPhone(Long user_phone);

	// check user already exits or not
	@Query(value = "SELECT * FROM user res where res.user_phone = ?1", nativeQuery = true)
	public UserModel checkUserExits(Long user_phone);

	@Transactional
	@Modifying
	@Query(value = "delete from user where admin_id = ?1", nativeQuery = true)
	public void deleteUserbyAdminId(Long admin_id);
}
