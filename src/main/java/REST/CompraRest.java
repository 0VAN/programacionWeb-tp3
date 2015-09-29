package REST;

import EJB.Service.CompraService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Rest para Compras
 *
 * Created by szalimben on 28/09/15.
 */
@Path("/compras")
public class CompraRest {

	@EJB
	CompraService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompras(@Context UriInfo info) {
		return Response.status(200).entity(service.getCompras(info.getQueryParameters())).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompra(@PathParam("id") int id) {
		return Response.status(200).entity(service.getCompra(id)).build();
	}

}
