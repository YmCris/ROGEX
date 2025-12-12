package ymcris.rogex;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
//@ApplicationPath("resources")
@ApplicationPath("api/v1")
//public class JakartaRestConfiguration extends Application{
public class JakartaRestConfiguration extends ResourceConfig {
    
    public JakartaRestConfiguration() {
        packages("ymcris.rogex.a.resources").register(MultiPartFeature.class);
    }
}
