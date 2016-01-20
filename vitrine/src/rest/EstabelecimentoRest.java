package rest;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import modelo.Estabelecimento;
import modelo.Usuario;
import modelo.UsuarioEstabelecimento;

import org.jboss.ejb3.annotation.SecurityDomain;

import servico.EstabelecimentoFacade;
import servico.ItemFacade;
import servico.UsuarioEstabelecimentoFacade;
import util.Ejb;

@SecurityDomain("vitrineRealm")
@Stateless
//@RolesAllowed("ADMIN")
@Path("/estabelecimentos")
public class EstabelecimentoRest {

	private EstabelecimentoFacade estabelecimentoFacade;
	private UsuarioEstabelecimentoFacade usuarioEstabelecimentoFacade;

	public EstabelecimentoRest() {
		estabelecimentoFacade = Ejb.lookup(EstabelecimentoFacade.class);
		usuarioEstabelecimentoFacade = Ejb.lookup(UsuarioEstabelecimentoFacade.class);
	}
	
	/* Cria um novo estabelecimento.
	 * 
	 * @returns o estabelecimento criado.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response criar(@Context HttpServletRequest httpServletRequest, 
			Estabelecimento estabelecimento) throws Exception {
		Usuario usuario = (Usuario)httpServletRequest.getSession().getAttribute("usuario");
		return Response.ok()
				.entity(
						estabelecimentoFacade
						.criar(usuario, estabelecimento))
				.build();
	}

	/* Lista todos os estabelecimentos.
	 * 
	 * @returns lista de estabelecimentos.
	 */
	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar()  throws Exception {
		return Response.ok()
				.entity(
						estabelecimentoFacade.listar())
				.build();
	}
	
	/* Recuperar um estabelecimento pelo Id.
	 * 
	 * @returns o estabelecimento recuperado.
	 */
	//TODO: Está retornando status 200(ok) quando id não existe. Discutir se não é melhor outro status.
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperar(@PathParam("id") Long id) throws Exception{
		return Response.ok()
				.entity(
						estabelecimentoFacade.recuperar(id))
				.build();
	}

	@GET
	@Path("/usuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarEstabelecimentoDoUsuario(@Context HttpServletRequest httpServletRequest) 
			throws Exception {
		Estabelecimento estabelecimento = null;
		Usuario usuario = (Usuario)httpServletRequest.getSession().getAttribute("usuario");
		UsuarioEstabelecimento usuarioEstabelecimento = usuarioEstabelecimentoFacade
				.recuperarEstabelecimento(usuario);
		if (usuarioEstabelecimento != null) {
			estabelecimento = usuarioEstabelecimento.getEstabelecimento();
		}
		return Response.ok()
				.entity(estabelecimento)
				.build();
	}

	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam("id") Long id) throws Exception {
		estabelecimentoFacade.excluir(
				estabelecimentoFacade.recuperar(id));
		return Response.ok()
				.build();
	}


}
