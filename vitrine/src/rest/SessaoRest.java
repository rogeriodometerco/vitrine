package rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import modelo.Estabelecimento;
import modelo.Usuario;
import modelo.UsuarioEstabelecimento;
import servico.UsuarioEstabelecimentoFacade;
import servico.UsuarioFacade;
import util.Ejb;

@Path("/sessao")
public class SessaoRest {

	@Context 
	private HttpServletRequest httpServletRequest;
	private UsuarioFacade usuarioFacade;
	private UsuarioEstabelecimentoFacade usuarioEstabelecimentoFacade;
	
	public SessaoRest() {
		usuarioFacade = Ejb.lookup(UsuarioFacade.class);
		usuarioEstabelecimentoFacade = Ejb.lookup(UsuarioEstabelecimentoFacade.class);
	}
	
	@POST
	public Response login(Map<String, String> params) throws Exception {
		String email = params.get("email");
		String senha = params.get("senha");
		try {
			HttpSession httpSession = httpServletRequest.getSession();
			httpServletRequest.login(email, senha);
			Usuario usuario = usuarioFacade.recuperarPeloEmail(email);
			Estabelecimento estabelecimento = null;
			UsuarioEstabelecimento usuarioEstabelecimento = usuarioEstabelecimentoFacade
					.recuperarEstabelecimento(usuario);
			if (usuarioEstabelecimento != null) {
				estabelecimento = usuarioEstabelecimento.getEstabelecimento();
			}
			// TODO Avaliar colocação do usuário na sessão, má prática. Veja #infoSessao.
			httpSession.setAttribute("usuario", usuario);
			httpSession.setAttribute("estabelecimento", estabelecimento);
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
		HttpSession httpSession = httpServletRequest.getSession();
		httpSession.removeAttribute("estabelecimento");
		httpSession.removeAttribute("usuario");		
		return Response.ok()
				.header("Cookies", "")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response infoSessao() throws WebApplicationException {
		Usuario usuario = null;
		Estabelecimento estabelecimento = null;
		Map<String, String> infoUsuario = null;
		HttpSession httpSession = httpServletRequest.getSession();
		Object o = httpSession.getAttribute("estabelecimento");
		if (o != null) {
			estabelecimento = (Estabelecimento) o;
		}
		o = httpSession.getAttribute("usuario");
		if (o != null) {
			usuario = (Usuario) o;
			infoUsuario = new HashMap<String, String>();
			infoUsuario.put("id", String.valueOf(usuario.getId()));
			infoUsuario.put("nome", usuario.getNome());
			infoUsuario.put("email", usuario.getEmail());
		}
		Map<String, Object> retorno = new HashMap<String, Object>();
		retorno.put("usuario", infoUsuario);
		retorno.put("estabelecimento", estabelecimento);
		retorno.put("autenticado", httpServletRequest.getRemoteUser() != null ? true : false);

		return Response
				.ok(retorno)
				.build();
	}

}
