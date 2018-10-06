/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.audit.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Surface
 */
@Entity
@Cacheable(false)
@Table(name = "audit_weibo", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditWeibo.findAll", query = "SELECT a FROM AuditWeibo a")
    , @NamedQuery(name = "AuditWeibo.findByWeiboId", query = "SELECT a FROM AuditWeibo a WHERE a.weiboId = :weiboId")
    , @NamedQuery(name = "AuditWeibo.findByIsAudited", query = "SELECT a FROM AuditWeibo a WHERE a.isAudited = :isAudited")})
public class AuditWeibo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "weibo_id")
    private Long weiboId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_audited")
    private boolean isAudited;
    @PrimaryKeyJoinColumn(name = "weibo_id", referencedColumnName = "id")
    @OneToOne(optional = false, cascade=CascadeType.ALL)
    private Weibo weibo;

    public AuditWeibo() {
    }

    public AuditWeibo(Long weiboId) {
        this.weiboId = weiboId;
    }

    public AuditWeibo(Long weiboId, boolean isAudited) {
        this.weiboId = weiboId;
        this.isAudited = isAudited;
    }

    public Long getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Long weiboId) {
        this.weiboId = weiboId;
    }

    public boolean getIsAudited() {
        return isAudited;
    }

    public void setIsAudited(boolean isAudited) {
        this.isAudited = isAudited;
    }

    public Weibo getWeibo() {
        return weibo;
    }

    public void setWeibo(Weibo weibo) {
        this.weibo = weibo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (weiboId != null ? weiboId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditWeibo)) {
            return false;
        }
        AuditWeibo other = (AuditWeibo) object;
        if ((this.weiboId == null && other.weiboId != null) || (this.weiboId != null && !this.weiboId.equals(other.weiboId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "weiboadmin.audit.entity.AuditWeibo[ weiboId=" + weiboId + " ]";
    }
    
}
