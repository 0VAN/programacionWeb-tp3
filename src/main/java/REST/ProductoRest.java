package REST;

import EJB.Service.ProductoService;

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
 * Rest para Producto
 * Created by szalimben on 28/09/15.
 */
@Path("/productos")
public class ProductoRest {

    @EJB
    ProductoService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos(@Context UriInfo info) {
        return Response.status(200).entity(service.getProductos(info.getQueryParameters())).build();
    }

    @GET
    @Path("/proveedor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosByProveedor(@PathParam("id") int id) {
        return Response.status(200).entity(service.getProductosByProveedor(id)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVenta(@PathParam("id") int id) {
        return Response.status(200).entity(service.getProducto(id)).build();
    }

}
