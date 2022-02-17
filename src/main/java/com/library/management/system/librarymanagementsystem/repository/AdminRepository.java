package com.library.management.system.librarymanagementsystem.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import com.library.management.system.librarymanagementsystem.model.AdminModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long> {

	// update admin without password
	@Transactional
	@Modifying
	@Query(value = "update admin a set a.admin_username = ?1,a.role_id = ?2 where a.admin_id = ?3", nativeQuery = true)
	void updateAdminById(String admin_username, Long admin_role, Long admin_id);

	// change password
	@Transactional
	@Modifying
	@Query(value = "update admin a set a.admin_password = ?1 where a.admin_id = ?2", nativeQuery = true)
	void changeAdminPassword(String admin_password, Long admin_id);

	// get admin details by id
	@Query(value = "SELECT * FROM admin res where res.admin_id = ?1", nativeQuery = true)
	public AdminModel getAdmin(Long admin_id);

	// search admin using like
	@Query(value = "SELECT * FROM admin res where res.admin_username like ?1%", nativeQuery = true)
	public Optional<AdminModel> searchAdminUserName(String admin_username);

	// get admin password when username is right
	@Query(value = "SELECT admin_password FROM admin res where res.admin_username = ?1", nativeQuery = true)
	public String loginWithAdmin(String admin_username);

	// check admin already exits or not
	@Query(value = "SELECT * FROM admin res where res.admin_username = ?1", nativeQuery = true)
	public AdminModel checkAdminIsExits(String admin_username);

}
