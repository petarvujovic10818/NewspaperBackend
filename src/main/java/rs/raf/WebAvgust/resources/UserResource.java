package rs.raf.WebAvgust.resources;


import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import rs.raf.WebAvgust.entities.User;
import rs.raf.WebAvgust.requests.LoginRequest;
import rs.raf.WebAvgust.services.UserService;

import javax.annotation.Generated;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;

import org.apache.commons.codec.digest.DigestUtils;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(){
        return Response.ok(this.userService.allUsers()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User create(@Valid User user) {
        return this.userService.addUser(user);
    }

    @DELETE
    @Path("/{email}")
    public void delete(@PathParam("email") String email){
        this.userService.deleteUser(email);
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser(@PathParam("email") String email){
        return this.userService.findUser(email);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateUser(User user){
        this.userService.updateUser(user);
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response loginResponse(@Valid LoginRequest loginRequest){
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        } else if(jwt.equals("INACTIVE")){
            return Response.status(423, "Access denied").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }

}
