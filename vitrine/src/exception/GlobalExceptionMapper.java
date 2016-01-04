package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

	
	public Response toResponse(Exception e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	}
}
