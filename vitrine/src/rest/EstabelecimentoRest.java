package rest;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.ejb3.annotation.SecurityDomain;

import modelo.Estabelecimento;
import servico.EstabelecimentoFacade;
import util.Ejb;

@SecurityDomain("vitrineRealm")
@Stateless
@RolesAllowed("ADMIN")
@Path("/estabelecimento")
public class EstabelecimentoRest {

	private EstabelecimentoFacade facade;
	
	private EstabelecimentoFacade getFacade() {
		if (facade == null) {
			facade = Ejb.lookup(EstabelecimentoFacade.class);
		}
		return facade;
	}
	
	/* Cria um novo estabelecimento.
	 * 
	 * @returns o estabelecimento criado.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrar(Estabelecimento estabelecimento) throws Exception {
		return Response.ok()
				.entity(
						getFacade().salvar(estabelecimento))
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
						getFacade().listar())
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
						getFacade().recuperar(id))
				.build();
	}

	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam("id") Long id) throws Exception {
		getFacade().excluir(
				getFacade().recuperar(id));
		return Response.ok()
				.build();
	}

	/*
	@OPTIONS 
	public Response options()  throws Exception {
		return Response.ok()
				.build();
	}
	*/	

}
