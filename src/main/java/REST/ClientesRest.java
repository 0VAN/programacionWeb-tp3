package REST;

import EJB.Jackson.Cliente;
import EJB.Service.ClienteService;
import EJB.Service.FilesService;
import JPA.ClienteEntity;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

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
 * Rest para Clientes
 * Created by szalimben on 28/09/15.
 */
@Path("/clientes")
public class ClientesRest {
    @EJB
    FilesService filesService;
    @EJB
    ClienteService service;
    // datos clientes.json
    private String nombre;

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
    @Path("/exportar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exportAllCliente(@Context UriInfo info) {
        return Response.status(200).entity(service.exportAllClientes(info.getQueryParameters())).build();
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
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultipartFormDataInput is) {
//        String result = getStringFromInputStream(is);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jfactory = objectMapper.getJsonFactory();

        JsonParser jParser = null;
        String a = "";
        try {
            jParser = jfactory.createJsonParser(a);
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
                    String cedula = jParser.getText();

                    // como es el ultimo campo procesamos el cliente en persistencia
                    filesService.addCliente(nombre, cedula);
                }
            }
            filesService.terminarStateful();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return Response.status(200).entity("ok").build();
    }

}
