package com.example.bankcards.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;


@Entity
@Table(name = "users")
public class User {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "role_id")
    private int roleId;

    @Email
    private String email;

    private String password;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRoleId() {
        return roleId;
    }

    public  String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
