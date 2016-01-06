package rest;

import java.util.Date;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import modelo.Item;

import servico.ItemFacade;
import util.Ejb;

@Path("/itens")
//@RequestScoped
public class ItemRest {  
	private Logger logger = Logger.getLogger(
			getClass().getName());

	//@Inject
	//ItemFacade facade;

	private ItemFacade facade;

	@PostConstruct
	public void postConstruct() {
		if (facade == null) {
			facade = Ejb.lookup(ItemFacade.class);
		}
	}

	//private ItemFacade getFacade() {
	//	if (facade == null) {
	//		facade = Ejb.lookup(ItemFacade.class);
	//	}
	//	return facade;
	//}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrar(Item item) throws WebApplicationException {
		logger.log(Level.INFO, item.toString());
		Item itemPersistido;
		try {
			Date dataSistema = new Date();
			item.setDataTransacao(dataSistema);

			itemPersistido = facade.salvar(item);
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

}
