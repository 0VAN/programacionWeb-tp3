package REST;

import EJB.Service.ProveedorService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Rest para Proveedores
 * Created by szalimben on 28/09/15.
 */
@Path("/proveedores")
public class ProveedoresRest {

    @EJB
    ProveedorService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVentas(@Context UriInfo info) {
        return Response.status(200).entity(service.getProveedores(info.getQueryParameters())).build();
    }
}
