package ymcris.rogex.g.commons.response;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

/**
 * The JSONResponse class is the class responsible for be the template of a
 * Response to send to angular
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class GenericJSONResponse {

    // SPECIFIC METHODS --------------------------------------------------------
    /**
     * Function responsible for send the response to tomcat to send to angular
     * to show on the web browser
     *
     * @param message message of the response
     * @param status code status
     * @return response of the request
     */
    public Response sendJSONResponse(String message, Status status) {
        return Response.status(status)
                .entity(createJSONObject(message))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    /**
     * Function responsible for create the JSON object to send
     *
     * @param message to create the json
     * @return json Object created
     */
    private JsonObject createJSONObject(String message) {
        return Json.createObjectBuilder()
                .add("message", message)
                .build();
    }

}
