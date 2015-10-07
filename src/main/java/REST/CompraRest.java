package REST;

import EJB.Jackson.Compra;
import EJB.Service.CompraService;
import EJB.Util.StockInsuficienteException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

/**
 * Rest para Compras
 * <p>
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

    @POST
    @Consumes("application/json")
    public Response crearCompras(String content) {
        System.out.println(content);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Compra compra = mapper.readValue(content, Compra.class);
            service.addCompra(compra);

        } catch (IOException e) {
            e.printStackTrace();
            return Response
                    .status(409)
                    .entity(e.getMessage()).build();
        } catch (StockInsuficienteException e) {
            return Response
                    .status(409)
                    .entity(e.getMessage()).build();
        }
        return Response.status(201).build();
    }

}
