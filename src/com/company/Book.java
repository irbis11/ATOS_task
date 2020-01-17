package com.company;

public class Book {

    private String title;
    private int year;
    private String author;
    private int ID;
    private static int counter = 0;
    private boolean isLent;
    private String lenderName;

    public Book (String title, int year, String author){
        counter++;
        this.author = author;
        this.year = year;
        this.title = title;
        this.ID = counter;
        this.isLent = false;
        this.lenderName = "";
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + this.title + '\'' + ", year=" + this.year + ", author='" + this.author + '\''
                + ", isLent=" + this.isLent + ", lenderName='" + this.lenderName + '\'' + '}';
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getID() {
        return this.ID;
    }

    public String getLenderName() {
        return this.lenderName;
    }

    public boolean isLent() {
        return this.isLent;
    }

    public void lentBook(String name) {
        this.lenderName = name;
        this.isLent = true;
    }

    public void returnBook() {
        this.lenderName = "";
        this.isLent = false;
    }

}