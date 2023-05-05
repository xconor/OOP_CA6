package org.example;

public class Game {
    private int id;
    private String title;
    private float rating;
    private int releaseYear;
    private String developer;
    private String platform;

    // Constructor
    public Game(int id, String title, float rating, int releaseYear, String developer, String platform) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.developer = developer;
        this.platform = platform;
    }

    // Getters and setters
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
    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public String getDeveloper() {
        return developer;
    }
    public void setDeveloper(String developer) {
        this.developer = developer;
    }
    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", title=" + title + ", rating=" + rating +
                ", releaseYear=" + releaseYear + ", developer=" + developer +
                ", platform=" + platform + "]";
    }

}
