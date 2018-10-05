/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.audit.entity;

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
@Table(name = "user", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByMail", query = "SELECT u FROM User u WHERE u.mail = :mail")
    , @NamedQuery(name = "User.findByNickname", query = "SELECT u FROM User u WHERE u.nickname = :nickname")
    , @NamedQuery(name = "User.findByIntroduction", query = "SELECT u FROM User u WHERE u.introduction = :introduction")
    , @NamedQuery(name = "User.findByProfilePicture", query = "SELECT u FROM User u WHERE u.profilePicture = :profilePicture")
    , @NamedQuery(name = "User.findBySalt", query = "SELECT u FROM User u WHERE u.salt = :salt")
    , @NamedQuery(name = "User.findBySaltPass", query = "SELECT u FROM User u WHERE u.saltPass = :saltPass")
    , @NamedQuery(name = "User.findByIsDeleted", query = "SELECT u FROM User u WHERE u.isDeleted = :isDeleted")
    , @NamedQuery(name = "User.findByProfileDate", query = "SELECT u FROM User u WHERE u.profileDate = :profileDate")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nickname")
    private String nickname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "introduction")
    private String introduction;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "profile_picture")
    private String profilePicture;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "salt")
    private String salt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "salt_pass")
    private String saltPass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "profile_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date profileDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Weibo> weiboCollection;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String mail, String nickname, String introduction, String profilePicture, String salt, String saltPass, boolean isDeleted, Date profileDate) {
        this.id = id;
        this.mail = mail;
        this.nickname = nickname;
        this.introduction = introduction;
        this.profilePicture = profilePicture;
        this.salt = salt;
        this.saltPass = saltPass;
        this.isDeleted = isDeleted;
        this.profileDate = profileDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSaltPass() {
        return saltPass;
    }

    public void setSaltPass(String saltPass) {
        this.saltPass = saltPass;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getProfileDate() {
        return profileDate;
    }

    public void setProfileDate(Date profileDate) {
        this.profileDate = profileDate;
    }

    @XmlTransient
    public Collection<Weibo> getWeiboCollection() {
        return weiboCollection;
    }

    public void setWeiboCollection(Collection<Weibo> weiboCollection) {
        this.weiboCollection = weiboCollection;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "weiboadmin.audit.entity.User[ id=" + id + " ]";
    }
    
}
