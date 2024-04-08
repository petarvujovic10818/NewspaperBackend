package rs.raf.WebAvgust.repositories;

import rs.raf.WebAvgust.entities.Cookie;
import rs.raf.WebAvgust.entities.News;
import rs.raf.WebAvgust.entities.User;

import java.util.List;

public interface NewsRepository {

    public News addNews(News news);
    public List<News> allNews();
    public News findNews(int id);
    public void deleteNews(int id);
    public List<News> newsByCategory(String category);
    public void updateNews(News news);
    public List<News> newsBySearch(String search);
    public List<News> newsByTag(String tag);
    public Cookie addCookie(Cookie cookie);
    public List<Cookie> findCookie(String value, int newsId);



}
