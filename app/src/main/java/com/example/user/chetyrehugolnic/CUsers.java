package com.example.user.chetyrehugolnic;

/**
 * Created by sokol on 13.08.2018.
 */

public class CUsers {


    public CUsers(int usID, int grID, String aEmail, String aPas, String lName, String fName, String aInfo){
        user_ID=usID;
        group_ID=grID;
        email=aEmail;
        password=aPas;
        lastName=lName;
        first_name=fName;
        info=aInfo;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstname(String firstname) {
        this.first_name = firstname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public int getGroup_ID() {
        return group_ID;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstname() {
        return first_name;
    }

    public String getInfo() {
        return info;
    }

    protected int user_ID,group_ID;
    protected String email,lastName,info,password;
    protected String first_name;

}
