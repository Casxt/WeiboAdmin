/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.audit.boundary;

import java.util.List;
import javax.ejb.Stateless;
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
import weiboadmin.audit.entity.AuditWeibo;
import weiboadmin.AbstractFacade;

/**
 *
 * @author Surface
 */
@Stateless
@Path("audit")
public class AuditWeiboFacadeREST extends AbstractFacade<AuditWeibo> {

    @PersistenceContext(unitName = "weiboadmin_weiboadmin_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public AuditWeiboFacadeREST() {
        super(AuditWeibo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(AuditWeibo entity) {
        super.create(entity);
    }

    /**
     * PUT 方法用于封禁微博
     *
     * @param id
     * @param entity
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AuditWeiboDTO edit(@PathParam("id") Long id, AuditWeibo entity) {//
        try{
            super.edit(entity);
        }catch(Exception e){
            return new AuditWeiboDTO("Failed", "Update Weibo Failed");
        }
        return new AuditWeiboDTO("Success", "Update Weibo Success");
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AuditWeibo find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AuditWeibo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AuditWeibo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("{audited}/{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AuditWeiboDTO getAuditedWeibo(@PathParam("audited") String audited, @PathParam("from") Integer from, @PathParam("to") Integer to) {
        AuditWeiboDTO auditWeiboDTO;
        try {
            List<AuditWeibo> auditWeibo = (List<AuditWeibo>) em.createQuery("SELECT a FROM AuditWeibo a WHERE a.isAudited= :isAudited ORDER BY a.weiboId DESC")
                    // 是否是编辑过的
                    .setParameter("isAudited", audited.equals("audited"))
                    .setFirstResult(from)
                    .setMaxResults(to - from + 1)
                    .getResultList();
            auditWeiboDTO = new AuditWeiboDTO("Success", "Get Audit Weibo Success", auditWeibo);
        } catch (Exception e) {
            auditWeiboDTO = new AuditWeiboDTO("Failed", "Get Audit Weibo Failed");
        }
        return auditWeiboDTO;
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
