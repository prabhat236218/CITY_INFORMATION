package com.example.prabhat.city_information;

/**
 * Created by prabhat on 27/8/17.
 */

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String mobile;
    private  String dob;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setMobile(String mobile)
    {
        this.mobile=mobile;
    }
    public String getMobile()
    {
        return mobile;
    }
    public void setDob(String dob)
    {
        this.dob=dob;
    }

    public String getDob()
    {
        return dob;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



