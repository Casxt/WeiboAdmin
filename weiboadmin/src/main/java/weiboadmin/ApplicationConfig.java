/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin;
import java.util.Set;
import javax.ws.rs.core.Application;
/**
 *
 * @author Surface
 */


@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(weiboadmin.admin.boundary.AdministratorFacadeREST.class);
        resources.add(weiboadmin.admin.boundary.PermissionFacadeREST.class);
        resources.add(weiboadmin.audit.boundary.AuditWeiboFacadeREST.class);
        resources.add(weiboadmin.audit.boundary.CommentFacadeREST.class);
        resources.add(weiboadmin.audit.boundary.WeiboFacadeREST.class);
    }
}