package REST;

import EJB.Jackson.Cliente;
import EJB.Service.ClienteFileService;
import EJB.Service.ClienteService;
import EJB.Service.FilesService;
import JPA.ClienteEntity;
import com.google.gson.Gson;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

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
    @EJB
    private ClienteFileService clienteFileService;
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
        return Response
                .ok(service.exportAllClientes(info.getQueryParameters()))
                .header("Content-Disposition", "attachment; filename=clientes.json").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliente(@PathParam("id") int id) {
        return Response.status(200).entity(service.getCliente(id)).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteCliente(@PathParam("id") int id) {
        return new Gson().toJson(service.deleteCliente(id));
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

//    @POST
//    @Path("/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(@FormDataParam("fileCliente") InputStream is) {
//        String result = getStringFromInputStream(is);
//        clienteFileService.parsear(result);
//        return Response.status(200).entity("ok").build();
//    }
//@POST
//@Path("/upload")
//@Consumes(MediaType.MULTIPART_FORM_DATA)
//public Response uploadFile(
//        @FormDataParam("fileCliente") InputStream uploadedInputStream,
//        @FormDataParam("fileCliente") FormDataContentDisposition fileDetail) {
//
//    String uploadedFileLocation = "d://uploaded/" + fileDetail.getFileName();
//
//    // save it
//    writeToFile(uploadedInputStream, uploadedFileLocation);
//
//    String output = "File uploaded to : " + uploadedFileLocation;
//
//    return Response.status(200).entity(output).build();
//
//}
//
//    // save uploaded file to new location
//    private void writeToFile(InputStream uploadedInputStream,
//                             String uploadedFileLocation) {
//
//        try {
//            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            out = new FileOutputStream(new File(uploadedFileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//
//    }

    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    public Response uploadFile(@MultipartForm FileUploadForm form) {

        String fileName = "d:\\anything";

//        try {
//            writeFile(form.getData(), fileName);
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }

        System.out.println("Done");

        return Response.status(200)
                .entity("uploadFile is called, Uploaded file name : " + fileName).build();

    }

}


class FileUploadForm {

    private byte[] data;

    public FileUploadForm() {
    }

    public byte[] getData() {
        return data;
    }

    @FormParam("uploadedFile")
    @PartType("application/octet-stream")
    public void setData(byte[] data) {
        this.data = data;
    }

}
