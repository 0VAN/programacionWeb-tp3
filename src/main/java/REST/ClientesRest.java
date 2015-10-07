package REST;

import EJB.Helper.VentasResponse;
import EJB.Jackson.Cliente;
import EJB.Service.ClienteService;
import EJB.Service.FilesService;
import JPA.ClienteEntity;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.sun.jersey.multipart.FormDataParam;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.InputStream;

/**
 * Rest para Clientes
 * Created by szalimben on 28/09/15.
 */
@Path("/clientes")
public class ClientesRest {
    @EJB
    FilesService filesService;
    private JsonFactory jfactory;
    private JsonParser jParser;
    // datos clientes.json
    private String nombre;
    private String cedula;

    @EJB
    ClienteService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientes(@Context UriInfo info) {
        return Response.status(200).entity(service.getClientes(info.getQueryParameters())).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCliente() {
        return Response.status(200).entity(service.getAllClientes()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliente(@PathParam("id") int id) {
        return Response.status(200).entity(service.getCliente(id)).build();
    }

    @POST
    @Consumes("application/json")
    public Response crearCliente(String content) {
        System.out.println(content);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Cliente cliente = mapper.readValue(content, Cliente.class);
            ClienteEntity clienteEntity = new ClienteEntity();
            clienteEntity.setNombre(cliente.getNombre());
            clienteEntity.setCedulaIdentidad(cliente.getCedula());
            service.add(clienteEntity);

        } catch (IOException e) {
            e.printStackTrace();
            return Response
                    .status(409)
                    .entity(e.getMessage()).build();
        }
        return Response.status(201).build();
    }

    @POST
    @Path("/uploadFileClientes")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormDataParam("fileCliente") InputStream is) {
        jfactory = new JsonFactory();

        try {
            jParser = jfactory.createParser(is);

            jParser.nextToken(); // token '{'
            String texto1 = jParser.getText();
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
                    filesService.addCliente(nombre, cedula);
                }
            }
            filesService.terminarStateful();
        }catch(Exception e){
            // Procesamos la excepcion
        }

        return Response.status(200).entity("ok").build();
    }

}
