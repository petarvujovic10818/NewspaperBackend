package rs.raf.WebAvgust.repositories;

import rs.raf.WebAvgust.entities.Category;
import rs.raf.WebAvgust.entities.Comment;

import java.util.List;

public interface CommentRepository {

    public Comment addComment(Comment comment);
    public List<Comment> allComments(int news);
    public void deleteComments(int newsId);

}
