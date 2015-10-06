package REST;

import EJB.Jackson.Cliente;
import EJB.Service.ClienteService;
import JPA.ClienteEntity;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

/**
 * Rest para Clientes
 * Created by szalimben on 28/09/15.
 */
@Path("/clientes")
public class ClientesRest {

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

}
