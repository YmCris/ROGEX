package ymcris.rogex.a.resources.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ymcris.rogex.b.services.login.LogInService;
import ymcris.rogex.c.dtos.login.LogInRequest;
import ymcris.rogex.c.dtos.login.LogInResponse;
import ymcris.rogex.g.commons.response.GenericJSONResponse;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The LogInResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 27, 2025
 */
@Path("auth")
public class LogInResource {

    // HTTP METHODS ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // SPECIFIC METHODS --------------------------------------------------------
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(LogInRequest request,
            @Context HttpServletRequest httpRequest) {

        LogInService service = new LogInService();
        GenericJSONResponse jSONResponse = new GenericJSONResponse();

        try {

            LogInResponse response = service.getRole(request);

            //Create the session with jakartaa (to don't depend of the local storage in angular)
            HttpSession session = httpRequest.getSession(true);

            session.setAttribute("USER", response);

            return Response.ok(response).build();

        } catch (ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.UNAUTHORIZED);

        }

    }

    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    public Response me(@Context HttpServletRequest httpRequest) {

        HttpSession session = httpRequest.getSession(false);

        if (session == null) {

            return Response.status(Response.Status.UNAUTHORIZED).build();

        }

        LogInResponse response = (LogInResponse) session.getAttribute("USER");

        if (response == null) {

            return Response.status(Response.Status.UNAUTHORIZED).build();

        }

        return Response.ok(response).build();
    }

    @POST
    @Path("/logout")
    public Response logout(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.invalidate();

        }

        return Response.ok().build();
    }

}
