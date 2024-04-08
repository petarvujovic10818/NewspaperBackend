package rs.raf.WebAvgust.entities;

public class Cookie {

    private int id;

    private String value;
    private int newsId;

    public Cookie(){

    }

    public Cookie(int id, String value, int newsId){
        this.id = id;
        this.value = value;
        this.newsId=newsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
}
