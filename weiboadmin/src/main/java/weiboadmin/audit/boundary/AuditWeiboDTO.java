/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.audit.boundary;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import weiboadmin.BasicDTO;
import weiboadmin.admin.entity.Administrator;
import weiboadmin.audit.entity.AuditWeibo;
import weiboadmin.audit.entity.Weibo;

/**
 *
 * @author Surface
 */
@XmlRootElement
public class AuditWeiboDTO extends BasicDTO {

    public AuditWeiboDTO() {
    }

    public AuditWeiboDTO(String State, String Msg) {
        super(State, Msg);
    }

    public AuditWeiboDTO(String State, String Msg, String Detail) {
        super(State, Msg, Detail);
    }

    public AuditWeiboDTO(String State, String Msg, List<AuditWeibo> AuditWeibo) {
        super(State, Msg);
        this.AuditWeibo = AuditWeibo;
    }

    public AuditWeiboDTO(String State, String Msg, String Detail, List<AuditWeibo> AuditWeibo) {
        super(State, Msg, Detail);
        this.AuditWeibo = AuditWeibo;
    }
    
    private List<AuditWeibo> AuditWeibo;

    public List<AuditWeibo> getAuditWeibo() {
        return AuditWeibo;
    }

    public void setAuditWeibo(List<AuditWeibo> AuditWeibo) {
        this.AuditWeibo = AuditWeibo;
    }
}

