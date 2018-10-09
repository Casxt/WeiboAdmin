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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import weiboadmin.audit.entity.Comment;

/**
 *
 * @author Surface
 */
@Stateless
@Path("comment")
public class CommentFacadeREST {

    @PersistenceContext(unitName = "weiboadmin_weiboadmin_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CommentFacadeREST() {
    }
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public CommentTimeCountDTO countREST() {
        List<CommentTimeCount> commentTimeCount = new LinkedList<>();
        List<Object> resultList = em.createQuery("SELECT COUNT( c.id ) AS num, FUNC('DATE_TRUNC_SECOND', c.commentDate) AS time FROM Comment c GROUP BY time ORDER BY time")
                .getResultList();
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
            Object[] result = (Object[]) iterator.next();
            Integer count = ((Long) result[0]).intValue();
            Date time = (Date) result[1];
            commentTimeCount.add(new CommentTimeCount(count, time));
        }
        return new CommentTimeCountDTO("Success", "Get Time Count Success", commentTimeCount);
    }
}
