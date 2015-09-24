package REST;

import EJB.Helper.VentasResponse;
import EJB.Service.VentasService;
import EJB.Util.StockInsuficienteException;
import JPA.MODEL.VentaEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Type;

/**
 * Created by alex on 31/08/15.
 */

@Path("/ventas")
public class VentasRest {

    private VentasResponse response;

    @EJB
    VentasService ventasService;

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllVentas() {
//        return Response.status(200).entity(ventasService.getVentas()).build();
//    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVenta(@PathParam("id") int id) {
        return Response.status(200).entity(ventasService.getVenta(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVentas(@Context UriInfo info) {
        return Response.status(200).entity(ventasService.getVentas(info.getQueryParameters())).build();
    }


    @POST
    @Consumes("application/json")
    public Response crearVentas(String content) {
        Type tipoVenta = new TypeToken<VentasResponse>() {}.getType();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        VentaEntity venta = gson.fromJson(content, tipoVenta);
        try {
            ventasService.addVenta(venta);
        } catch (StockInsuficienteException e) {
            return Response
                    .status(409)
                    .entity(e.getMessage()).build();

        }
        return Response.status(201).build();
    }

}
