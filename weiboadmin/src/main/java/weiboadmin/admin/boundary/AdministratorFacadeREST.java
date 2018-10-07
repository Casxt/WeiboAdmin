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
public class AdministratorFacadeREST {

    @PersistenceContext(unitName = "weiboadmin_weiboadmin_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    @EJB
    AdminService adminService;

    public AdministratorFacadeREST() {
        //super(Administrator.class);
    }

    /**
     * 创建管理员账号
     * @param administrator
     * @return 
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AdministratorDTO create(Administrator administrator) {
        try {
            em.persist(administrator);
            em.flush();
            return new AdministratorDTO("Success", "create admin success", administrator);//
        } catch (Exception e) {
            return new AdministratorDTO("Failed", "create admin error", administrator);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Administrator entity) {
        em.merge(entity);
        em.flush();
    }

    /*
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
     */
    /**
     * 用户登陆
     * @param user
     * @param pass
     * @return 
     */
    @GET
    @Path("{user}/{pass}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AdministratorDTO getAdminInfo(@PathParam("user") String user, @PathParam("pass") String pass) {
        Administrator administrator = adminService.SignIn(user, pass);
        if (administrator == null) {
            return new AdministratorDTO("Failed", "user or pass error", administrator);//
        }
        return new AdministratorDTO("Success", "SignIn Success", administrator);//
    }

    /*
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
*/

}
