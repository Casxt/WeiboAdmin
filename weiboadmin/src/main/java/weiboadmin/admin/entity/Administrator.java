/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.admin.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Surface
 */
@Entity
@Table(name = "administrator", schema = "admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrator.findAll", query = "SELECT a FROM Administrator a")
    , @NamedQuery(name = "Administrator.findById", query = "SELECT a FROM Administrator a WHERE a.id = :id")
    , @NamedQuery(name = "Administrator.findByNickname", query = "SELECT a FROM Administrator a WHERE a.nickname = :nickname")
    , @NamedQuery(name = "Administrator.findByHashPass", query = "SELECT a FROM Administrator a WHERE a.hashPass = :hashPass")
    , @NamedQuery(name = "Administrator.findByIsDelete", query = "SELECT a FROM Administrator a WHERE a.isDelete = :isDelete")
    , @NamedQuery(name = "Administrator.findByAdministratorDate", query = "SELECT a FROM Administrator a WHERE a.administratorDate = :administratorDate")})
public class Administrator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nickname")
    private String nickname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "hash_pass")
    private String hashPass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_delete")
    private boolean isDelete;
    //@Basic(optional = false)
    //@NotNull
    //@CreationTimestamp
    @Column(name = "administrator_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date administratorDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Permission> permissionCollection;

    public Administrator() {
    }

    public Administrator(Long id) {
        this.id = id;
    }

    public Administrator(Long id, String nickname, String hashPass, boolean isDelete, Date administratorDate) {
        this.id = id;
        this.nickname = nickname;
        this.hashPass = hashPass;
        this.isDelete = isDelete;
        this.administratorDate = administratorDate;
    }
    
    @PrePersist
    protected void onCreate() {
    administratorDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHashPass() {
        return hashPass;
    }

    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getAdministratorDate() {
        return administratorDate;
    }

    public void setAdministratorDate(Date administratorDate) {
        this.administratorDate = administratorDate;
    }

    @XmlTransient
    public Collection<Permission> getPermissionCollection() {
        return permissionCollection;
    }

    public void setPermissionCollection(Collection<Permission> permissionCollection) {
        this.permissionCollection = permissionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrator)) {
            return false;
        }
        Administrator other = (Administrator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "weiboadmin.admin.entity.Administrator[ id=" + id + " ]";
    }
    
}
