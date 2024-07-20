package com.example.fxdemo;
public class Book {
    private String name;
    private String author;
    private String year;
    private String publisher;
    private String serial;
    public Book(String name, String author, String year,String publisher, String serial) {
        this.name = name;
        this.author = author;
        this.year =year;
        this.publisher = publisher;
        this.serial = serial;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getSerial() {
        return serial;
    }
    public void setSerial(String serial) {
        this.serial = serial;
    }

}

