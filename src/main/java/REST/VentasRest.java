package REST;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import EJB.VentasService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;


import java.io.InputStream;

/**
 * Created by alex on 31/08/15.
 */

@Path("/ventas")
public class VentasRest {

    private JsonFactory jfactory;
    private JsonParser jParser;

    // datos clientes.json
    private String nombre;
    private String cedula;

    // datos compras.json
    private String fecha;
    private String proveedor;
    private String descripcion;
    private String precio;
    private String stock;
    private String cantidad;

    // datos ventas.json

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
        jfactory = new JsonFactory();

        try {
            jParser = jfactory.createJsonParser(is);

            jParser.nextToken(); // token '{'
            jParser.nextToken(); // token 'clientes'

            // se procesa cada objeto cliente, primer token '['
            while (jParser.nextToken() != JsonToken.END_ARRAY) {

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
        }catch(Exception e){
            // Procesamos la excepcion
        }

        return Response.status(200).entity("ok").build();
    }


    @POST
    @Path("/uploadFileCompras")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormDataParam("file") InputStream is) {
        jfactory = new JsonFactory();

        try {
            jParser = jfactory.createJsonParser(is);

            jParser.nextToken(); // token '{'
            jParser.nextToken(); // token 'compras'

            // se procesa cada compra individualmente, primer token '['
            while (jParser.nextToken() != JsonToken.END_ARRAY) {

                String fieldname = jParser.getCurrentName();
                if ("fecha".equals(fieldname)) {

                    // token 'fecha'
                    // vamos al siguiente token, el valor de 'fecha'
                    jParser.nextToken();
                    fecha = jParser.getText();
                }

                if ("proveedor".equals(fieldname)) {

                    // token 'proveedor'
                    // vamos al siguiente token, el valor de 'proveedor'
                    jParser.nextToken();
                    proveedor = jParser.getText();
                }

                if ("productos".equals(fieldname)) {

                    while (jParser.nextToken() != JsonToken.END_ARRAY) {
                        String field = jParser.getCurrentName();
                        if ("descripcion".equals(field)) {

                            // token 'descripcion'
                            // vamos al siguiente token, el valor de 'descripcion'
                            jParser.nextToken();
                            descripcion = jParser.getText();
                        }

                        if ("precio".equals(field)) {

                            // token 'precio'
                            // vamos al siguiente token, el valor de 'precio'
                            jParser.nextToken();
                            precio = jParser.getText();
                        }

                        if ("stock".equals(field)) {

                            // token 'stock'
                            // vamos al siguiente token, el valor de 'stock'
                            jParser.nextToken();
                            stock = jParser.getText();
                        }

                        if ("cantidad".equals(field)) {

                            // token 'cantidad'
                            // vamos al siguiente token, el valor de 'cantidad'
                            jParser.nextToken();
                            cantidad = jParser.getText();

                            // como es el ultimo campo procesamos el producto en persistencia
                        }
                    }
                }
            }
        }catch(Exception e){
            // Procesamos la excepcion
        }

        return Response.status(200).entity("ok").build();
    }

}
