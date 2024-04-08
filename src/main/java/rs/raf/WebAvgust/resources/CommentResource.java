package rs.raf.WebAvgust.resources;

import rs.raf.WebAvgust.entities.Category;
import rs.raf.WebAvgust.entities.Comment;
import rs.raf.WebAvgust.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comments")
public class CommentResource {

    @Inject
    CommentService commentService;

    @GET
    @Path("/{news}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(@PathParam("news") int news){
        return Response.ok(this.commentService.allComments(news)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment create(@Valid Comment comment) {
        return this.commentService.addComment(comment);
    }

    @DELETE
    @Path("/{newsId}")
    public void deleteComments(@PathParam("newsId") int newsId){
        this.commentService.deleteComments(newsId);
    }


}
