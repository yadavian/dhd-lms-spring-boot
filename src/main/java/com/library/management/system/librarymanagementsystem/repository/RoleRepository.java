package com.library.management.system.librarymanagementsystem.repository;

import com.library.management.system.librarymanagementsystem.model.RoleModel;

import org.apache.catalina.mbeans.RoleMBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<RoleModel,Long> {
    

       // check admin already exits or not
       @Query(value = "SELECT * FROM role res where res.role_name = ?1", nativeQuery = true)
       public RoleModel getRoleData(String role_name);
   

}
