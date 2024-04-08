package rs.raf.WebAvgust.repositories;

import rs.raf.WebAvgust.entities.Comment;
import rs.raf.WebAvgust.entities.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository{

    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            String[] generatedColumns = {"id"};
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO comments (author, text, date, news, likes, dislikes) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1,comment.getAuthor());
            preparedStatement.setString(2,comment.getText());
            preparedStatement.setLong(3,comment.getDate());
            preparedStatement.setInt(4, comment.getNews());
            preparedStatement.setInt(5, comment.getLikes());
            preparedStatement.setInt(6, comment.getDislikes());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public List<Comment> allComments(int news) {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            //statement = connection.createStatement();
            //resultSet = statement.executeQuery("select * from news where category = ?");
            preparedStatement = connection.prepareStatement("SELECT * FROM comments where news = ?");
            preparedStatement.setInt(1,news);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                comments.add(new Comment(resultSet.getInt("id"),resultSet.getString("author"), resultSet.getString("text"),resultSet.getLong("date"), resultSet.getInt("news"),
                        resultSet.getInt("likes"),resultSet.getInt("dislikes")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }

    @Override
    public void deleteComments(int newsId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM comments where news = ?");
            preparedStatement.setInt(1, newsId);
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
}
