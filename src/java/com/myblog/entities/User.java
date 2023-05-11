package com.myblog.entities;

/**
 *
 * @author Devender
 */
public class User {
    private int user_id;
    private String name;
    private String email;
    private String password;
    private String gender;

    public User(String name, String email, String password, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User(int user_id) {
        this.user_id = user_id;
    }
    public User(int user_id, String name, String email, String password, String gender) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
    public User() {
    }
    
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }    
}
