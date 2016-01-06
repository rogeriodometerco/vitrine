package exception;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

	@Context 
	private HttpServletResponse httpServletResponse;

	public Response toResponse(Exception e) {
		System.out.println("GlobalExceptionMapper Status: " + httpServletResponse.getStatus());
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	}
}
