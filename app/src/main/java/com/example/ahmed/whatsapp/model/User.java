package com.example.ahmed.whatsapp.model;

/**
 * Created by ahmed on 30/08/17.
 */

public class User {

    String userEmail = "engahmedali2022@gmail.com";
    String userPassword = "123456";
    String userUserName = "ahmed20130074";//the lastname or firstname or knickname
    String userGender = "Male";
    String userDOB = "7/8/95";
    //the photo in the future


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }

    public User(String userEmail, String userPassword, String userUserName, String userGender, String userDOB) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userUserName = userUserName;
        this.userGender = userGender;
        this.userDOB = userDOB;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserUserName() {
        return userUserName;
    }

    public String getUserDOB() {
        return userDOB;
    }

    public String getUserGender() {
        return userGender;
    }
}
