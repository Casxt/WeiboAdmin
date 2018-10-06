/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.admin.boundary;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import weiboadmin.admin.control.AdminService;
import weiboadmin.admin.entity.Administrator;
import weiboadmin.AbstractFacade;

/**
 *
 * @author Surface
 */
@Stateless
@Path("administrator")
public class AdministratorFacadeREST extends AbstractFacade<Administrator> {

    @PersistenceContext(unitName = "weiboadmin_weiboadmin_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    @EJB
    AdminService adminService;

    public AdministratorFacadeREST() {
        super(Administrator.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Administrator entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Administrator entity) {
        super.edit(entity);
        em.flush();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{user}/{pass}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AdministratorDTO getAdminInfo(@PathParam("user") String user, @PathParam("pass") String pass) {
        Administrator administrator = adminService.SignIn(user, pass);
         AdministratorDTO adminstratorDTO = new AdministratorDTO();
        if(administrator == null){
            adminstratorDTO = new AdministratorDTO("Failed","user or pass error",administrator);//
        } else {
            adminstratorDTO = new AdministratorDTO("Success","SignIn Success",administrator);//
        }
        return adminstratorDTO;
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Administrator find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Administrator> findAll() {
        return super.findAll();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
