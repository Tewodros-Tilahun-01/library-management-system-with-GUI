package com.example.fxdemo;

public class BorrowedBook {
    private String username;
    private String serialnumber;

    public BorrowedBook(String username, String serialnumber) {
        this.username = username;
        this.serialnumber = serialnumber;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        this.username = username;
    }
    public String getSerialnumber() {
        return serialnumber;
    }
    public void setSerialnumber(String author) {
        this.serialnumber = serialnumber;
    }


}
