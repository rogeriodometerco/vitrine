package rest;

import java.util.logging.Logger;

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
import modelo.Item;
import servico.EstabelecimentoFacade;
import servico.ItemFacade;
import util.Ejb;

@Path("/itens")
public class ItemRest {  
	private Logger logger = Logger.getLogger(
			getClass().getName());

	private ItemFacade itemFacade;
	private EstabelecimentoFacade estabelecimentoFacade;

	public ItemRest() {
		itemFacade = Ejb.lookup(ItemFacade.class);
		estabelecimentoFacade = Ejb.lookup(EstabelecimentoFacade.class);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar()  throws Exception {
		return Response.ok()
				.entity(
						itemFacade.listar())
				.build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperar(@PathParam("id") Long id) throws Exception{
		Item item;
		try {
			item = itemFacade.recuperar(id);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
		return Response.ok()
				.entity(item)
				.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response criar(@Context HttpServletRequest httpServletRequest, Item item) 
			throws WebApplicationException {
		Item itemPersistido;
		try {
			Object o = httpServletRequest.getSession().getAttribute("estabelecimento");
			Estabelecimento estabelecimento = null;
			if (o != null) {
				estabelecimento = (Estabelecimento)o;
			}
			item.setEstabelecimento(estabelecimento);
			itemPersistido = itemFacade.salvar(item);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
		return Response.ok()
				.entity(itemPersistido)
				.build();
	}


	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam("id") Long id) throws Exception {
		itemFacade.excluir(
				itemFacade.recuperar(id));
		return Response.ok()
				.build();
	}

	
	@GET
	@Path("/estabelecimento/{estabelecimentoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarParaGerenciar(@PathParam("estabelecimentoId") Long estabelecimentoId) 
			throws WebApplicationException {
		
		try {
			Estabelecimento estabelecimento = estabelecimentoFacade.recuperar(estabelecimentoId);
			return Response.ok()
					.entity(
							itemFacade.listarPorEstabelecimento(estabelecimento))
					.build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
}