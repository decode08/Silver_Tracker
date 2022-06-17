package com.example.silver_tracker.Models;

public class Users {
    String userName, contact, mail, password, repassword;


    public Users(String userName, String mail, String password, String userId) {
        this.userName = userName;
        this.contact = contact;
        this.mail = mail;
        this.password = password;

    }

    public Users() {
    }
//

    public Users(String userName, String mail, String password, String userId, String contact, String repassword) {
        this.userName = userName;
        this.contact = contact;
        this.mail = mail;
        this.password = password;
        this.repassword = repassword;
    }

    public Users(String toString, String toString1, String toString2, String toString3, String toString4) {

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}