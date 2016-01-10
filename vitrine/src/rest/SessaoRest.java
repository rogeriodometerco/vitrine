package rest;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/sessao")
public class SessaoRest {

	@Context 
	private HttpServletRequest httpServletRequest;
	
	@POST
	public Response login(Map<String, String> params) throws Exception {
		String email = params.get("email");
		String senha = params.get("senha");
		System.out.println(email + " - " + senha);
		try {
			httpServletRequest.getSession();
			httpServletRequest.login(email, senha);
		} catch (ServletException e) {
			e.printStackTrace();
			throw e;
		}
		return Response.ok()
				.header("Cookies", "JSESSIONID=" + httpServletRequest.getSession().getId())
				.build();
	}
	
	@DELETE
	public Response logout() throws Exception {
		httpServletRequest.logout();
		return Response.ok()
				.header("Cookies", "")
				.build();
	}
	
	@GET
	public Response usuarioEstaLogado() throws WebApplicationException {
		System.out.println("nome: " + httpServletRequest.getAttribute("nome"));
		
		httpServletRequest.setAttribute("nome", "Rogerio Fernando Dometerco");
		return Response
				.ok(httpServletRequest.getRemoteUser() != null ? true : false)
				.build();
	}

}

