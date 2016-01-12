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

@Path("/itens")
public class ItemRest {  
	private Logger logger = Logger.getLogger(
			getClass().getName());

	private ItemFacade facade;

	private ItemFacade getFacade() {
		if (facade == null) {
			facade = Ejb.lookup(ItemFacade.class);
		}
		return facade;
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
			itemPersistido = getFacade().salvar(item);
			logger.log(Level.INFO,"Item persistido: " + itemPersistido.toString());
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
		logger.log(Level.INFO, "Pós persistir.");
		return Response.ok()
				.entity(itemPersistido)
				.build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperar(@PathParam("id") Long id) throws Exception{
		Item item;
		try {
			item = facade.recuperar(id);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
		return Response.ok()
				.entity(
						item)
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarParaCliente()  throws Exception {
		
		return Response.ok()
				.entity(
						getFacade().listarParaCliente())
				.build();
	}
	
	@GET
	@Path("/gerente")
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
							getFacade().listarParaGerenciar(estabelecimento))
					.build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam("id") Long id) throws Exception {
		
		getFacade().excluir(
				getFacade().recuperar(id));
		return Response.ok()
				.build();
	}

	
}
