package com.maryna.leonardodavinci;

public class Artwork {
    private String title;
    private int year;
    private int imageResourceId;

    public Artwork(String title, int year, int imageResourceId) {
        this.title = title;
        this.year = year;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
