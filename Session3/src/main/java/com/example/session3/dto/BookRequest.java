package com.example.session3.dto;

public class BookRequest {

    private Long authorId;
    private String title;
    private double price;

    public BookRequest() {
    }

    public BookRequest(Long authorId, String title, double price) {
        this.authorId = authorId;
        this.title = title;
        this.price = price;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
