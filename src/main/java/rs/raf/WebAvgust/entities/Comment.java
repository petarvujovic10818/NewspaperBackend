package rs.raf.WebAvgust.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Comment {

    private int id;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String author;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String text;
    private long date;
    private int news;
    private int likes;
    private int dislikes;

    public Comment(){

    }

    public Comment(int id, String author, String text, long date, int news, int likes, int dislikes){
        this.id = id;
        this.author = author;
        this.text = text;
        this.date = date;
        this.news = news;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getNews() {
        return news;
    }

    public void setNews(int news) {
        this.news = news;
    }
}
