import org.glassfish.jersey.process.internal.RequestScoped;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Aljaz on 25/10/2017.
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("profile")
public class ProfileResource {

    @POST
    @Path("packets-delivered")
    public Response packetsDelivered(User user) {
        int packetsDelivered = -1;
        if (user.getUsername().equals("zanozbot")) {
            packetsDelivered = 928;
        } else if (user.getUsername().equals("aljazb")) {
            packetsDelivered = 294;
        }
        return Response.ok(packetsDelivered).build();
    }

    @GET
    @Path("health")
    public Response health() {
        return Response.ok("OK").build();
    }
}
