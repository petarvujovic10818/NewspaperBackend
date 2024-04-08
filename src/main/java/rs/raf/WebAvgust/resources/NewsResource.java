package rs.raf.WebAvgust.resources;

import rs.raf.WebAvgust.entities.Cookie;
import rs.raf.WebAvgust.entities.News;
import rs.raf.WebAvgust.services.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Random;

@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(){
        return Response.ok(this.newsService.allNews()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News create(@Valid News news) {
        return this.newsService.addNews(news);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateNews(@Valid News news){this.newsService.updateNews(news);}

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News findNews(@PathParam("id") int id){
        return this.newsService.findNews(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id){
        this.newsService.deleteNews(id);
    }

    @GET
    @Path("/this/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newsByCategory(@PathParam("category") String category){
        return Response.ok(this.newsService.newsByCategory(category)).build();
    }

    @GET
    @Path("/that/{tag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newsByTag(@PathParam("tag") String tag){
        return Response.ok(this.newsService.newsByTag(tag)).build();
    }

    @GET
    @Path("/find/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newsBySearch(@PathParam("search") String search){
        return Response.ok(this.newsService.newsBySearch(search)).build();
    }

    @GET
    @Path("/test")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCookie(){
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRST".toCharArray();

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String randomStr = sb.toString();

        NewCookie cookie = new NewCookie("cookie", randomStr);
        return Response.ok(cookie).build();
    }

    @POST
    @Path("/cookie")
    @Produces(MediaType.APPLICATION_JSON)
    public Cookie addCookie(@Valid Cookie cookie){
        return this.newsService.addCookie(cookie);
    }

    @GET
    @Path("/cookie/{value}/{newsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCookie(@PathParam("value") String value, @PathParam("newsId") int newsId){
        return Response.ok(this.newsService.findCookie(value,newsId)).build();
    }

}
