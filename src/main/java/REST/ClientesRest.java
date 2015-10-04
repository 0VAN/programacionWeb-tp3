package REST;

import EJB.Service.ClienteService;

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
 * Rest para Clientes
 * Created by szalimben on 28/09/15.
 */
@Path("/clientes")
public class ClientesRest {

    @EJB
    ClienteService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientes(@Context UriInfo info) {
        return Response.status(200).entity(service.getClientes(info.getQueryParameters())).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliente(@PathParam("id") int id) {
        return Response.status(200).entity(service.getCliente(id)).build();
    }

}
