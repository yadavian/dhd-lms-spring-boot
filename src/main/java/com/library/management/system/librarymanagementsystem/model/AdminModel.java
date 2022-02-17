package com.library.management.system.librarymanagementsystem.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;


@Entity
@Table(name="admin")

public class AdminModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long admin_id;
    @Column(name = "admin_username",unique = true)
    private String admin_username;
    @Column(name = "admin_password")
    private String admin_password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleModel roleModel;

    


    public AdminModel() {
    }

   


    public AdminModel(Long admin_id, String admin_username, String admin_password, RoleModel roleModel) {
        this.admin_id = admin_id;
        this.admin_username = admin_username;
        this.admin_password = admin_password;
        this.roleModel = roleModel;
    }




    public RoleModel getRoleModel() {
        return roleModel;
    }




    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }




    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }

    // public String getAdmin_password() {
    //     return admin_password;
    // }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

   

  
    @Override
    public String toString() {
        return "AdminModel [admin_id=" + admin_id + ", admin_password=" + admin_password + ","
                + ", admin_username=" + admin_username + "]";
    }



    
}
