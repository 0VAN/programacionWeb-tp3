package REST;

import EJB.Helper.VentasResponse;
import EJB.Jackson.Venta;
import EJB.Service.FilesService;
import EJB.Service.VentaFileService;
import EJB.Service.VentasService;
import EJB.Util.StockInsuficienteException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.sun.jersey.multipart.FormDataParam;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by alex on 31/08/15.
 */

@Path("/ventas")
public class VentasRest {

    @EJB
    VentaFileService ventaFileService;

    @EJB
    FilesService filesService;
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
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVentas() {
        return Response.status(200).entity(ventasService.getAllVentas()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVentas(@Context UriInfo info) {
        return Response.status(200).entity(ventasService.getVentas(info.getQueryParameters())).build();
    }





    @POST
    @Path("/uploadFileVentas")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormDataParam("fileVenta") InputStream is) {
        String result = getStringFromInputStream(is);
        ventaFileService.parsear(result);

        return Response.status(200).entity("ok").build();
    }


    @POST
    @Consumes("application/json")
    public Response crearVentas(String content) {
        System.out.println(content);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Venta venta = mapper.readValue(content, Venta.class);
            System.out.println(venta.getClienteId());
            ventasService.addVenta(venta);

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

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

}
