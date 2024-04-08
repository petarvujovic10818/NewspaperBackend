package rs.raf.WebAvgust.repositories;

import org.omg.Messaging.SyncScopeHelper;
import rs.raf.WebAvgust.entities.Cookie;
import rs.raf.WebAvgust.entities.News;
import rs.raf.WebAvgust.entities.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class MySqlNewsRepository extends MySqlAbstractRepository implements NewsRepository{

    @Override
    public News addNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            String[] generatedColumns = {"id"};
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO news (title, text, author, date, category, tags, count) VALUES(?, ?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1,news.getTitle());
            preparedStatement.setString(2,news.getText());
            preparedStatement.setString(3,news.getAuthor());
            preparedStatement.setLong(4, news.getDate());
            preparedStatement.setString(5, news.getCategory());
            preparedStatement.setString(6, news.getTags());
            preparedStatement.setInt(7, news.getCount());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                news.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;

    }

    @Override
    public List<News> allNews() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from news");
            while(resultSet.next()){
                news.add(new News(resultSet.getInt("id"),resultSet.getString("title"), resultSet.getString("text"),resultSet.getString("author"),resultSet.getLong("date"), resultSet.getString("category"),
                        resultSet.getString("tags"), resultSet.getInt("count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public News findNews(int id) {
        News news = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int newsId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                String author = resultSet.getString("author");
                long date = resultSet.getLong("date");
                String category = resultSet.getString("category");
                String tags = resultSet.getString("tags");
                int count = resultSet.getInt("count");
                news = new News(newsId, title, text, author,date, category, tags, count);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public void deleteNews(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM news where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<News> newsByCategory(String category) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            //statement = connection.createStatement();
            //resultSet = statement.executeQuery("select * from news where category = ?");
            preparedStatement = connection.prepareStatement("SELECT * FROM news where category = ?");
            preparedStatement.setString(1,category);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                news.add(new News(resultSet.getInt("id"),resultSet.getString("title"), resultSet.getString("text"),resultSet.getString("author"),resultSet.getLong("date"), resultSet.getString("category"),
                        resultSet.getString("tags"), resultSet.getInt("count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public void updateNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE news SET title=?, text=?, category=?, tags=?, count=? WHERE id=?");
            preparedStatement.setString(1,news.getTitle());
            preparedStatement.setString(2,news.getText());
            preparedStatement.setString(3,news.getCategory());
            preparedStatement.setString(4, news.getTags());
            preparedStatement.setInt(5, news.getCount());
            preparedStatement.setInt(6,news.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<News> newsBySearch(String search) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            //statement = connection.createStatement();
            //resultSet = statement.executeQuery("select * from news where category = ?");
            preparedStatement = connection.prepareStatement("SELECT * FROM news where text LIKE ? OR title LIKE ?");
            preparedStatement.setString(1,"%" + search +"%");
            preparedStatement.setString(2,"%"+search+"%");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                news.add(new News(resultSet.getInt("id"),resultSet.getString("title"), resultSet.getString("text"),resultSet.getString("author"),resultSet.getLong("date"), resultSet.getString("category"),
                        resultSet.getString("tags"), resultSet.getInt("count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public List<News> newsByTag(String tag) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            //statement = connection.createStatement();
            //resultSet = statement.executeQuery("select * from news where category = ?");
            preparedStatement = connection.prepareStatement("SELECT * FROM news where tags LIKE ?");
            preparedStatement.setString(1,"%" + tag +"%");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                news.add(new News(resultSet.getInt("id"),resultSet.getString("title"), resultSet.getString("text"),resultSet.getString("author"),resultSet.getLong("date"), resultSet.getString("category"),
                        resultSet.getString("tags"), resultSet.getInt("count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public Cookie addCookie(Cookie cookie) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            String[] generatedColumns = {"id"};
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO cookies (value, newsId) VALUES(?, ?)", generatedColumns);
            preparedStatement.setString(1,cookie.getValue());
            preparedStatement.setInt(2, cookie.getNewsId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                cookie.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return cookie;
    }

    @Override
    public List<Cookie> findCookie(String value, int newsId) {
        List<Cookie> cookies = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            //statement = connection.createStatement();
            //resultSet = statement.executeQuery("select * from news where category = ?");
            preparedStatement = connection.prepareStatement("SELECT * FROM cookies where value LIKE ? AND newsId LIKE ?");
            preparedStatement.setString(1,value);
            preparedStatement.setInt(2, newsId);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                cookies.add(new Cookie(resultSet.getInt("id"),resultSet.getString("value"), resultSet.getInt("newsId")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return cookies;
    }
}
