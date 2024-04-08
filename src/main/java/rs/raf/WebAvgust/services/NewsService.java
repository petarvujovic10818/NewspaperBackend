package rs.raf.WebAvgust.services;

import rs.raf.WebAvgust.entities.Cookie;
import rs.raf.WebAvgust.entities.News;
import rs.raf.WebAvgust.entities.User;
import rs.raf.WebAvgust.repositories.NewsRepository;

import javax.inject.Inject;
import java.util.List;

public class NewsService {

    public NewsService(){

    }

    @Inject
    private NewsRepository newsRepository;

    public News addNews(News news){
        return this.newsRepository.addNews(news);
    }

    public List<News> allNews() {
        return this.newsRepository.allNews();
    }

    public void deleteNews(int id){this.newsRepository.deleteNews(id);}

    public List<News> newsByCategory(String category){
        return this.newsRepository.newsByCategory(category);
    }

    public void updateNews(News news){
        this.newsRepository.updateNews(news);
    }

    public News findNews(int id){
        return this.newsRepository.findNews(id);
    }

    public List<News> newsBySearch(String search){
        return this.newsRepository.newsBySearch(search);
    }

    public List<News> newsByTag(String tag){
        return this.newsRepository.newsByTag(tag);
    }

    public Cookie addCookie(Cookie cookie){
        return this.newsRepository.addCookie(cookie);
    }

    public List<Cookie> findCookie(String value, int newsId){
        return this.newsRepository.findCookie(value, newsId);
    }

}
