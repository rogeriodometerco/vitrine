package rest;

import java.util.Map;

import javax.ejb.SessionContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/sessao")
public class SessaoRest {

	@Context 
	private HttpServletRequest httpServletRequest;

	@Context 
	private SessionContext context;

	@POST
	public Response login(Map<String, String> params) throws Exception {
		String email = params.get("email");
		String senha = params.get("senha");
		httpServletRequest.login(email, senha);
		return Response.ok()
				.header("Cookies", "JSESSIONID=" + httpServletRequest.getSession().getId())
				.build();



	}
}
