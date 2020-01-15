package com.company;

public class Book {

    private String title;
    private int year;
    private String author;
    private int ID;
    private static int counter;
    private boolean isLent;
    private String lenderName;

    public Book (String title, int year, String author){
        counter++;
        this.author = author;
        this.year = year;
        this.title = title;
        this.ID = counter;
        this.isLent = false;
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", year=" + year + ", author='" + author + '\'' + ", isLent=" + isLent + ", lenderName='" + lenderName + '\'' + '}';
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public int getID() {
        return ID;
    }

    public String getLenderName() {
        return lenderName;
    }

    public boolean isLent() {
        return isLent;
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