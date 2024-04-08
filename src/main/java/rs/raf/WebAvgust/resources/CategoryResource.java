package rs.raf.WebAvgust.resources;

import rs.raf.WebAvgust.entities.Category;
import rs.raf.WebAvgust.entities.News;
import rs.raf.WebAvgust.services.CategoryService;
import rs.raf.WebAvgust.services.NewsService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categories")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(){
        return Response.ok(this.categoryService.allCategories()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category create(@Valid Category category) {
        return this.categoryService.addCategory(category);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id){
        this.categoryService.deleteCategory(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateCategory(Category category){
        this.categoryService.updateCategory(category);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Category findCategory(@PathParam("id") int id){
        return this.categoryService.findCategory(id);
    }

}
