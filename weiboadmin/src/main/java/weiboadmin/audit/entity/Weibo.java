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
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Cacheable(false)
@Table(name = "weibo", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Weibo.findAll", query = "SELECT w FROM Weibo w")
    , @NamedQuery(name = "Weibo.findById", query = "SELECT w FROM Weibo w WHERE w.id = :id")
    , @NamedQuery(name = "Weibo.findByContent", query = "SELECT w FROM Weibo w WHERE w.content = :content")
    , @NamedQuery(name = "Weibo.findByIsDeleted", query = "SELECT w FROM Weibo w WHERE w.isDeleted = :isDeleted")
    , @NamedQuery(name = "Weibo.findByWeiboDate", query = "SELECT w FROM Weibo w WHERE w.weiboDate = :weiboDate")
    , @NamedQuery(name = "Weibo.findByCommentCount", query = "SELECT w FROM Weibo w WHERE w.commentCount = :commentCount")
    , @NamedQuery(name = "Weibo.findByIsBaned", query = "SELECT w FROM Weibo w WHERE w.isBaned = :isBaned")})
public class Weibo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "weiboId")
    private Collection<Comment> commentCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weibo_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date weiboDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "comment_count")
    private long commentCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_baned")
    private boolean isBaned;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(mappedBy = "forward")
    private Collection<Weibo> weiboCollection;
    @JoinColumn(name = "forward", referencedColumnName = "id")
    @ManyToOne
    private Weibo forward;
    /*
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "weibo")
    private AuditWeibo auditWeibo;
*/
    public Weibo() {
    }

    public Weibo(Long id) {
        this.id = id;
    }

    public Weibo(Long id, String content, boolean isDeleted, Date weiboDate, long commentCount, boolean isBaned) {
        this.id = id;
        this.content = content;
        this.isDeleted = isDeleted;
        this.weiboDate = weiboDate;
        this.commentCount = commentCount;
        this.isBaned = isBaned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getWeiboDate() {
        return weiboDate;
    }

    public void setWeiboDate(Date weiboDate) {
        this.weiboDate = weiboDate;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public boolean getIsBaned() {
        return isBaned;
    }

    public void setIsBaned(boolean isBaned) {
        this.isBaned = isBaned;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Weibo> getWeiboCollection() {
        return weiboCollection;
    }

    public void setWeiboCollection(Collection<Weibo> weiboCollection) {
        this.weiboCollection = weiboCollection;
    }

    public Weibo getForward() {
        return forward;
    }

    public void setForward(Weibo forward) {
        this.forward = forward;
    }
/*
    public AuditWeibo getAuditWeibo() {
        return auditWeibo;
    }

    public void setAuditWeibo(AuditWeibo auditWeibo) {
        this.auditWeibo = auditWeibo;
    }
*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Weibo)) {
            return false;
        }
        Weibo other = (Weibo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "weiboadmin.audit.entity.Weibo[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }
    
}
