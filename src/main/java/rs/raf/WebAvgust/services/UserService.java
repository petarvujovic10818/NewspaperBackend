package rs.raf.WebAvgust.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.WebAvgust.entities.User;
import rs.raf.WebAvgust.repositories.UserRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    public UserService(){

    }

    @Inject
    private UserRepository userRepository;

    public User addUser(User user){
        return this.userRepository.addUser(user);
    }

    public List<User> allUsers() {
        return this.userRepository.allUsers();
    }

    public void deleteUser(String email){this.userRepository.deleteUser(email);}

    public User findUser(String email){return this.userRepository.findUser(email);}


    public void updateUser(User user){
        this.userRepository.updateUser(user);
    }

    public String login(String username, String password){
        String hashedPassword = DigestUtils.sha256Hex(password);
        User user = this.userRepository.findUser(username);
        if (user == null || !user.getPassword().equals(hashedPassword)) {
            return null;
        } else if(user.getStatus().equals("INACTIVE")){
            return "INACTIVE";
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("geass");

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(username)
                .withClaim("tip", user.getTip())
                .withClaim("email",user.getEmail())
                .withClaim("status",user.getStatus())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("geass");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        String username = jwt.getSubject();
//        jwt.getClaim("role").asString();

        User user = userRepository.findUser(username);

        if (user == null){
            return false;
        }

        return true;
    }

}
