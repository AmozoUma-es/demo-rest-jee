package es.uma.informatica.sii.ejb.demo.web;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import es.uma.informatica.sii.ejb.demo.ejb.BaseDeDatosLocal;
import es.uma.informatica.sii.ejb.demo.entidades.Contacto;
import es.uma.informatica.sii.ejb.demo.entidades.Contactos;

@Path("/ws")
@RequestScoped
public class WebService {
    @Inject
    private BaseDeDatosLocal bbdd;
    @Context
    private UriInfo uriInfo;

	@GET
    @Path("/contacto/{id: [0-9]+}")
    @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getContacto(@PathParam("id") Long id) {
        Contacto contacto = bbdd.getContacto(id);
        return Response.ok(contacto).build();
    }
	
    @GET
    @Path("/contactos")
    @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response todosContactos() {
        List<Contacto> contactos = bbdd.todoContactos();
        Contactos todos = new Contactos(contactos);

        return Response.ok(todos).build();
    }

    @POST
    @Path("/creaContacto")
    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public Response creaContacto(Contacto c) {
        if (c == null)
            throw new BadRequestException();
        
        bbdd.aniadirContacto(c);
        URI contactoUri = uriInfo.getAbsolutePathBuilder().path(c.getId().toString()).build();
        return Response.created(contactoUri).build();
    }

    @PUT
    @Path("/actualizaContacto")
    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    public Response actualizaContacto(Contacto c) {
        if (c == null)
            throw new BadRequestException();
        
        bbdd.actualizarContacto(c);
        
        return Response.accepted(c).build();
    }
    
    @DELETE
    @Path("/borrarContacto/{id: [0-9]+}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    public Response borrarContacto(@PathParam("id") Long id) {
        Contacto contacto = bbdd.getContacto(id);
        if (contacto == null)
            throw new BadRequestException();
        
        bbdd.eliminarContacto(contacto);
        
        return Response.ok().build();
    }
}
