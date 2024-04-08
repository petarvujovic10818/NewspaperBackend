package rs.raf.WebAvgust;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.WebAvgust.repositories.*;
import rs.raf.WebAvgust.services.CategoryService;
import rs.raf.WebAvgust.services.CommentService;
import rs.raf.WebAvgust.services.NewsService;
import rs.raf.WebAvgust.services.UserService;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//osnovna ruta
@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication(){
        //validacija
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        //bindujemo implementacije
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);

                this.bind(MySqlNewsRepository.class).to(NewsRepository.class).in(Singleton.class);

                this.bind(MySqlCategoryRepository.class).to(CategoryRepository.class).in(Singleton.class);

                this.bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);

                this.bindAsContract(UserService.class);

                this.bindAsContract(NewsService.class);

                this.bindAsContract(CategoryService.class);

                this.bindAsContract(CommentService.class);


            }
        };
        register(binder);

        // Ucitavamo anotacije za cijeli projekat
        packages("rs.raf.WebAvgust");

    }

}
