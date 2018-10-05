/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.admin.control;

import java.util.Collection;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import weiboadmin.admin.entity.Administrator;
import weiboadmin.admin.entity.Permission;

/**
 *
 * @author Surface
 */
@Stateless
public class AdminService {
    @PersistenceContext(unitName = "weiboadmin_weiboadmin_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    /**
     * SignIn check user pass 
     * @param name
     * @param pass
     * @return Administrator if match; null if not match;
     */
    public Administrator SignIn(String name, String pass){
        try{
            return (Administrator) em.createQuery("SELECT a FROM Administrator a WHERE a.nickname = :nickname AND a.hashPass = :hashPass")
                    .setParameter("nickname", name)
                    .setParameter("hashPass", pass)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    
    /**
     * Auth Check user pass permission
     * @param name
     * @param pass
     * @param permission
     * @param type
     * @return true if has permission; false if no permission;
     */
    public boolean Auth(String name, String pass, String permission, String type){
        Administrator administrator = SignIn(name, pass);
        if (administrator==null){
            return false;
        }
        for (Iterator iterator = administrator.getPermissionCollection().iterator(); iterator.hasNext();) {
            Permission per = (Permission) iterator.next();
            if (per.getPermission().equals(permission) && per.getPermissionType().equals(type)){
                return true;
            }
        }
        return false;
    }
}
