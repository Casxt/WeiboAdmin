/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.admin.boundary;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import weiboadmin.BasicDTO;
import weiboadmin.admin.entity.Administrator;
/**
 *
 * @author Surface
 */
@XmlRootElement
public class AdministratorDTO extends BasicDTO {
    private Administrator Admin;
    
    public AdministratorDTO(String State, String Msg, Administrator Admin) {
        super(State, Msg);
        this.Admin = Admin;
    }

    public AdministratorDTO(String State, String Msg, String Detail, Administrator Admin) {
        super(State, Msg, Detail);
        this.Admin = Admin;
    }

    public AdministratorDTO() {
    }

    public Administrator getAdmin() {
        return Admin;
    }

    public void setAdmin(Administrator Admin) {
        this.Admin = Admin;
    }
}