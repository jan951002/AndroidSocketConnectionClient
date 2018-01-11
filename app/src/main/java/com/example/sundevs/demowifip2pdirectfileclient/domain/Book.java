package com.example.sundevs.demowifip2pdirectfileclient.domain;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by SunDevs on 4/01/2018.
 */

public class Book implements Serializable {

    private String title;
    private String author;
    private String bookCategory;

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public static Book create(String serializedData) {
        Gson gson = new Gson();
        return gson.fromJson(serializedData, Book.class);
    }

    public String serializeToJson() {
        // Serialize this class into a JSON string using GSON
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookCategory='" + bookCategory + '\'' +
                '}';
    }

    public void setAll(String title, String author, String category) {
        setTitle(title);
        setAuthor(author);
        setBookCategory(category);
    }
}
