/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SEdbLib;

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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mahzel
 */
@Entity
@Table(name = "galaxy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Galaxy.findAll", query = "SELECT g FROM Galaxy g"),
    @NamedQuery(name = "Galaxy.findById", query = "SELECT g FROM Galaxy g WHERE g.id = :id"),
    @NamedQuery(name = "Galaxy.findBySupStack", query = "SELECT g FROM Galaxy g WHERE g.supStack = :supStack"),
    @NamedQuery(name = "Galaxy.findByStack", query = "SELECT g FROM Galaxy g WHERE g.stack = :stack"),
    @NamedQuery(name = "Galaxy.findByLocalGroup", query = "SELECT g FROM Galaxy g WHERE g.localGroup = :localGroup"),
    @NamedQuery(name = "Galaxy.findByDate", query = "SELECT g FROM Galaxy g WHERE g.date = :date"),
    @NamedQuery(name = "Galaxy.findByName", query = "SELECT g FROM Galaxy g WHERE g.name = :name")})

public class Galaxy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "SupStack")
    private int supStack;
    @Basic(optional = false)
    @Column(name = "Stack")
    private int stack;
    @Basic(optional = false)
    @Column(name = "LocalGroup")
    private int localGroup;
    @Basic(optional = false)
    @Column(name = "Num")
    private int num;
    @Basic(optional = false)
    @Lob
    @Column(name = "Name")
    private String name;
    @Lob
    @Column(name = "ImgUrl")
    private String imgUrl;
    @Lob
    @Column(name = "Observ")
    private String observ;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parGal")
    private Collection<Nebula> nebulaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "galId")
    private Collection<Cluster> clusterCollection;
    @JoinColumn(name = "Discoverer", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Users discoverer;

    /**
     *
     */
    public Galaxy() {
    }

    /**
     *
     * @param id
     */
    public Galaxy(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param supStack
     * @param stack
     * @param localGroup
     * @param num
     * @param name
     * @param imgUrl
     * @param observ
     * @param date
     */
    public Galaxy(Integer id, int supStack, int stack, int localGroup, int num, String name, String imgUrl, String observ, Date date) {
        this.id = id;
        this.supStack = supStack;
        this.stack = stack;
        this.localGroup = localGroup;
        this.num = num;
        this.name = name;
        this.imgUrl = imgUrl;
        this.observ = observ;
        this.date = date;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getSupStack() {
        return supStack;
    }

    /**
     *
     * @param supStack
     */
    public void setSupStack(int supStack) {
        this.supStack = supStack;
    }

    /**
     *
     * @return
     */
    public int getStack() {
        return stack;
    }

    /**
     *
     * @param stack
     */
    public void setStack(int stack) {
        this.stack = stack;
    }

    /**
     *
     * @return
     */
    public int getLocalGroup() {
        return localGroup;
    }

    /**
     *
     * @param localGroup
     */
    public void setLocalGroup(int localGroup) {
        this.localGroup = localGroup;
    }

    /**
     *
     * @return
     */
    public int getNum() {
        return num;
    }

    /**
     *
     * @param num
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *
     */
    public void setName()
    {
        this.name = "RG "+supStack+"-"+stack+"-"+localGroup+"-"+num;
    }

    /**
     *
     * @return
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     *
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     *
     * @return
     */
    public String getObserv() {
        return observ;
    }

    /**
     *
     * @param observ
     */
    public void setObserv(String observ) {
        this.observ = observ;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Nebula> getNebulaCollection() {
        return nebulaCollection;
    }

    /**
     *
     * @param nebulaCollection
     */
    public void setNebulaCollection(Collection<Nebula> nebulaCollection) {
        this.nebulaCollection = nebulaCollection;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Cluster> getClusterCollection() {
        return clusterCollection;
    }

    /**
     *
     * @param clusterCollection
     */
    public void setClusterCollection(Collection<Cluster> clusterCollection) {
        this.clusterCollection = clusterCollection;
    }

    /**
     *
     * @return
     */
    public Users getDiscoverer() {
        return discoverer;
    }

    /**
     *
     * @param discoverer
     */
    public void setDiscoverer(Users discoverer) {
        this.discoverer = discoverer;
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
        if (!(object instanceof Galaxy)) {
            return false;
        }
        Galaxy other = (Galaxy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SEdbLib.Galaxy[ id=" + id + " ]";
    }
    
    /**
     *
     * @return
     */
    public String genText()
    {String text;
     text = "Name : "+this.getName()+"\n";
     text += "Discoverer : "+this.getDiscoverer().getUser()+"\n";
     text += "Obs : "+this.getObserv()+"\n";
     text += "ImgUrl : "+this.getImgUrl()+"\n\n";
     return text; 
    }
    
}
