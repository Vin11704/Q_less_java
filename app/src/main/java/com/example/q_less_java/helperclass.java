package com.example.q_less_java;

public class helperclass {
    String name,email, username, password;

    public helperclass(String name, String email, String password,String username) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username ;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public helperclass() {
    }


}
