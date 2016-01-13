package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//Para mostra erro completo no browser.
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

	
	public Response toResponse(Exception e) {
		e.printStackTrace();
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	}
}
	
