/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.audit.boundary;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import weiboadmin.audit.entity.Weibo;
import weiboadmin.AbstractFacade;
/**
 *
 * @author Surface
 */
@Stateless
@Path("weibo")
public class WeiboFacadeREST{

    @PersistenceContext(unitName = "weiboadmin_weiboadmin_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public WeiboFacadeREST() {
        //super(Weibo.class);
    }
        @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public WeiboTimeCountDTO countREST() {
        /*SELECT 
	COUNT( * ),
	EXTRACT ( YEAR FROM weibo_date ) AS YEAR,
	EXTRACT ( MONTH FROM weibo_date ) AS MONTH,
	EXTRACT ( DAY FROM weibo_date ) AS DAY,
	EXTRACT ( HOUR FROM weibo_date ) AS HOUR
FROM
	weibo 
GROUP BY
	YEAR,
	MONTH,
	DAY,
	HOUR,
	trunc(
	EXTRACT ( MINUTE FROM weibo_date ) / 6);*/
        List<WeiboTimeCount> weiboTimeCount = new LinkedList<>();
        List<Object> resultList = em.createQuery("SELECT COUNT( w.id ) AS num, FUNC('DATE_TRUNC_SECOND', w.weiboDate) AS time FROM Weibo w GROUP BY time ORDER BY time")
                .getResultList();
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
            Object[] result = (Object[]) iterator.next();
            Integer count = ((Long) result[0]).intValue();
            Date time = (Date) result[1];
            weiboTimeCount.add(new WeiboTimeCount(count, time));
        }
        return new WeiboTimeCountDTO("Success","Get Time Count Success", weiboTimeCount);
        //return em.createQuery("SELECT COUNT(w), SQL('DATE_TRUNC('second', ?)', w.weiboDate) AS t  FROM Weibo w GROUP BY t")
        //        .getResultList();
    }
/*
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Weibo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Weibo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Weibo find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Weibo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Weibo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
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
