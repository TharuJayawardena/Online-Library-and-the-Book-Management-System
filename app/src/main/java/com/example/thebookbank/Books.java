package com.example.thebookbank;

public class Books {
    String BookName;
    String BookDetail;
    int BookPhoto;

    public Books(String BookName, String BookDetail, int BookPhoto) {
        this.BookName = BookName;
        this.BookDetail = BookDetail;
        this.BookPhoto = BookPhoto;
    }




    public String getBookName() {
        return BookName;
    }

    public String getBookDetail() {
        return BookDetail;
    }

    public int getBookPhoto() {
        return BookPhoto;
    }
}
