package org.example;

public class Game {

    private int id;
    private String title;
    private float rating;
    private int releaseYear;

    public Game(int id, String title, float rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }
    public Game (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getYear() {
        return releaseYear;
    }

    public void setYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' + '\'' +
                ", rating=" + rating +
                '}';
    }
}
