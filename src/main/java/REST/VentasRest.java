package REST;

<<<<<<< HEAD
import EJB.Helper.VentasResponse;
import EJB.Jackson.Venta;
import EJB.Service.FilesService;
import EJB.Service.VentasService;
import EJB.Util.StockInsuficienteException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
=======
import EJB.VentasService;
>>>>>>> 9bcd6f9406646f340fafc6c516a7c095d5796ac8

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> 9bcd6f9406646f340fafc6c516a7c095d5796ac8

/**
 * Created by alex on 31/08/15.
 */

@Path("/ventas")
public class VentasRest {

    @EJB
    FilesService filesService;
    @EJB
    VentasService ventasService;
    private VentasResponse response;
    private JsonFactory jfactory;
    private JsonParser jParser;
    // datos clientes.json
    private String nombre;
    private String cedula;
    // datos compras.json
    private String fechaCompra;
    private String montoCompra;
    private String proveedorCompra;
    private String productoIdCompra;
    private String cantidadCompra;
    // datos ventas.json
    private String fechaVenta;
    private String facturaIdVenta;
    private String clienteIdVenta;
    private String montoVenta;
    private String productoIdVenta;
    private String cantidadVenta;

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

//    @POST
//    @Path("/uploadFileClientes")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(@FormDataParam("file") InputStream is) {
//        jfactory = new JsonFactory();
//
//        try {
//            jParser = jfactory.createParser(is);
//
//            jParser.nextToken(); // token '{'
//            jParser.nextToken(); // token 'clientes'
//
//            // se procesa cada objeto cliente, primer token '['
//            while (jParser.nextToken() != JsonToken.END_ARRAY) {
//
//                String fieldname = jParser.getCurrentName();
//                if ("nombre".equals(fieldname)) {
//
//                    // token 'nombre'
//                    // vamos al siguiente token, el valor de 'nombre'
//                    jParser.nextToken();
//                    nombre = jParser.getText();
//                }
//
//                if ("cedula".equals(fieldname)) {
//
//                    // token 'cedula'
//                    // vamos al siguiente token, el valor de 'cedula'
//                    jParser.nextToken();
//                    cedula = jParser.getText();
//
//                    // como es el ultimo campo procesamos el cliente en persistencia
//                    filesService.addCliente(nombre, cedula);
//                }
//            }
//            filesService.terminarStateful();
//        }catch(Exception e){
//            // Procesamos la excepcion
//        }
//
//        return Response.status(200).entity("ok").build();
//    }
//
//
//    @POST
//    @Path("/uploadFileCompras")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(@FormDataParam("file") InputStream is) {
//        jfactory = new JsonFactory();
//
//        try {
//            jParser = jfactory.createParser(is);
//
//            jParser.nextToken(); // token '{'
//            jParser.nextToken(); // token 'compras'
//
//            // se procesa cada compra individualmente, primer token '['
//            while (jParser.nextToken() != JsonToken.END_ARRAY) {
//
//                String fieldname = jParser.getCurrentName();
//                if ("fecha".equals(fieldname)) {
//
//                    // token 'fecha'
//                    // vamos al siguiente token, el valor de 'fecha'
//                    jParser.nextToken();
//                    fechaCompra = jParser.getText();
//                }
//
//                if ("monto".equals(fieldname)) {
//
//                    // token 'monto'
//                    // vamos al siguiente token, el valor de 'monto'
//                    jParser.nextToken();
//                    montoCompra = jParser.getText();
//                }
//
//                if ("proveedor".equals(fieldname)) {
//
//                    // token 'proveedor'
//                    // vamos al siguiente token, el valor de 'proveedor'
//                    jParser.nextToken();
//                    proveedorCompra = jParser.getText();
//
//                    filesService.addCabeceraCompra(fechaCompra, montoCompra, proveedorCompra);
//                }
//
//                if ("productos".equals(fieldname)) {
//
//                    while (jParser.nextToken() != JsonToken.END_ARRAY) {
//                        String field = jParser.getCurrentName();
//
//                        if ("producto".equals(field)) {
//
//                            // token 'producto'
//                            // vamos al siguiente token, el valor de 'producto'
//                            jParser.nextToken();
//                            productoIdCompra = jParser.getText();
//                        }
//
//                        if ("cantidad".equals(field)) {
//
//                            // token 'cantidad'
//                            // vamos al siguiente token, el valor de 'cantidad'
//                            jParser.nextToken();
//                            cantidadCompra = jParser.getText();
//
//                            // como es el ultimo campo procesamos el producto en persistencia
//                            filesService.addCompraDetalle(productoIdCompra, cantidadCompra);
//                        }
//                    }
//                }
//            }
//            // termino el file entonces persistimos
//            filesService.addCompra();
//            filesService.terminarStateful();
//        }catch(Exception e){
//            // Procesamos la excepcion
//        }
//
//        return Response.status(200).entity("ok").build();
//    }
//
//
//    @POST
//    @Path("/uploadFileVentas")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(@FormDataParam("file") InputStream is) {
//        jfactory = new JsonFactory();
//
//        try {
//            jParser = jfactory.createParser(is);
//
//            jParser.nextToken(); // token '{'
//            jParser.nextToken(); // token 'ventas'
//
//            // se procesa cada compra individualmente, primer token '['
//            while (jParser.nextToken() != JsonToken.END_ARRAY) {
//
//                String fieldname = jParser.getCurrentName();
//                if ("fecha".equals(fieldname)) {
//
//                    // token 'fecha'
//                    // vamos al siguiente token, el valor de 'fecha'
//                    jParser.nextToken();
//                    fechaVenta = jParser.getText();
//                }
//
//                if ("factura".equals(fieldname)) {
//
//                    // token 'factura'
//                    // vamos al siguiente token, el valor de 'factura'
//                    jParser.nextToken();
//                    facturaIdVenta = jParser.getText();
//                }
//
//                if ("cliente".equals(fieldname)) {
//
//                    // token 'cliente'
//                    // vamos al siguiente token, el valor de 'cliente'
//                    jParser.nextToken();
//                    clienteIdVenta = jParser.getText();
//                }
//
//                if ("monto".equals(fieldname)) {
//
//                    // token 'monto'
//                    // vamos al siguiente token, el valor de 'monto'
//                    jParser.nextToken();
//                    montoVenta = jParser.getText();
//
//                    filesService.addCabeceraVenta(fechaVenta, facturaIdVenta,
//                            clienteIdVenta, montoVenta);
//                }
//
//                if ("ventas".equals(fieldname)) {
//
//                    while (jParser.nextToken() != JsonToken.END_ARRAY) {
//                        String field = jParser.getCurrentName();
//
//                        if ("producto".equals(field)) {
//
//                            // token 'producto'
//                            // vamos al siguiente token, el valor de 'producto'
//                            jParser.nextToken();
//                            productoIdVenta = jParser.getText();
//                        }
//
//                        if ("cantidad".equals(field)) {
//
//                            // token 'cantidad'
//                            // vamos al siguiente token, el valor de 'cantidad'
//                            jParser.nextToken();
//                            cantidadVenta = jParser.getText();
//
//                            // como es el ultimo campo procesamos el producto en persistencia
//                            filesService.addVentaDetalle(productoIdVenta, cantidadVenta);
//                        }
//                    }
//                }
//            }
//            // termino el file entonces persistimos
//            filesService.addVenta();
//            filesService.terminarStateful();
//        }catch(Exception e){
//            // Procesamos la excepcion
//        }
//
//        return Response.status(200).entity("ok").build();
//    }
//

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

}
