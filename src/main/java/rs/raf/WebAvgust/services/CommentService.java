package rs.raf.WebAvgust.services;

import rs.raf.WebAvgust.entities.Comment;
import rs.raf.WebAvgust.repositories.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    public CommentService(){

    }

    @Inject
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment){
        return this.commentRepository.addComment(comment);
    }

    public List<Comment> allComments(int news){
        return this.commentRepository.allComments(news);
    }

    public void deleteComments(int newsId){
        this.commentRepository.deleteComments(newsId);
    }

    public void likeComment(String cookie, int commentId){
        //find comment

    }

}
