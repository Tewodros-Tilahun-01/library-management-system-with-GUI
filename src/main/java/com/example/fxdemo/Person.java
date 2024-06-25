package com.example.fxdemo;

public class Person {
    private String username;
    private String id;

    public Person(String username, String id) {
        this.username = username;
        this.id = id;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        this.username = username;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
