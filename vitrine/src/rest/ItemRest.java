package rest;

import java.util.logging.Level;
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
import servico.ItemFacade;
import util.Ejb;

@Path("/item")
public class ItemRest {  
	private Logger logger = Logger.getLogger(
			getClass().getName());

	private ItemFacade itemFacade;

	public ItemRest() {
		itemFacade = Ejb.lookup(ItemFacade.class);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarParaCliente()  throws Exception {
		return Response.ok()
				.entity(
						itemFacade.listarParaCliente())
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
				.entity(
						item)
				.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response criar(@Context HttpServletRequest httpServletRequest, Item item) 
			throws WebApplicationException {
		logger.log(Level.INFO, item.toString());
		Item itemPersistido;
		try {
			Object o = httpServletRequest.getSession().getAttribute("estabelecimento");
			Estabelecimento estabelecimento = null;
			if (o != null) {
				estabelecimento = (Estabelecimento)o;
			}
			item.setEstabelecimento(estabelecimento);
			logger.log(Level.INFO, "Imagens: " + item.getImagens().get(0).getImagem());
			itemPersistido = itemFacade.salvar(item);
			logger.log(Level.INFO, "Imagens Persistidas: " + itemPersistido.getImagens().get(0).getImagem());
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
		logger.log(Level.INFO, "Pós persistir");
		return Response.ok()
				.entity(itemPersistido)
				.build();
	}


	@GET
	@Path("/estabelecimento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarParaGerenciar(@Context HttpServletRequest httpServletRequest) 
			throws WebApplicationException {
		
		try {
			Object o = httpServletRequest.getSession().getAttribute("estabelecimento");
			Estabelecimento estabelecimento = null;
			if (o != null) {
				estabelecimento = (Estabelecimento)o;
			}
			return Response.ok()
					.entity(
							itemFacade.listarParaGerenciar(estabelecimento))
					.build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam("id") Long id) throws Exception {
		
		itemFacade.excluir(
				itemFacade.recuperar(id));
		return Response.ok()
				.build();
	}

	
}