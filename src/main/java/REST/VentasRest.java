package REST;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import EJB.VentasService;


import java.io.InputStream;

/**
 * Created by alex on 31/08/15.
 */

@Path("/ventas")
public class VentasRest {

    private final JsonFactory jfactory = new JsonFactory();
    private JsonParser jsonParser = jfactory.createJsonParser(is);
    private String nombre;
    private String cedula;

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
    @Path("/uploadFileClientes")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormDataParam("file") InputStream is) {

        jParser.nextToken(); // token '{'
        jParser.nextToken(); // token 'clientes'

        // se procesa cada objeto cliente, primer token '['
        while(jParser.nextToken() != JsonToken.END_ARRAY){

            String fieldname = jParser.getCurrentName();
            if ("nombre".equals(fieldname)) {

                // token 'nombre'
                // vamos al siguiente token, el valor de 'nombre'
                jParser.nextToken();
                nombre = jParser.getText();
            }

            if ("cedula".equals(fieldname)) {

                // token 'cedula'
                // vamos al siguiente token, el valor de 'cedula'
                jParser.nextToken();
                cedula = jParser.getText();

                // como es el ultimo campo procesamos el cliente en persistencia
            }
        }

        return Response.status(200).entity("ok").build();
    }

}
