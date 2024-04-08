package rs.raf.WebAvgust.entities;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class News {

    private int id;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String title;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String text;
    private long date;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String category;
    private int count;
    private String author;
    private String tags;

    public News(){

    }

    public News(int id, String title, String text, String author, long date, String category, String tags, int count){
        this.id = id;
        this.title = title;
        this.text=text;
        this.author=author;
        this.date = date;
        this.category = category;
        this.tags = tags;
        this.count = count;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
