/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SEdbLib;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mahzel
 */
@Entity
@Table(name = "cluster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cluster.findAll", query = "SELECT c FROM Cluster c"),
    @NamedQuery(name = "Cluster.findById", query = "SELECT c FROM Cluster c WHERE c.id = :id"),
    @NamedQuery(name = "Cluster.findByDiscdate", query = "SELECT c FROM Cluster c WHERE c.discdate = :discdate"),
    @NamedQuery(name= "Cluster.findByGalId", query = "SELECT c FROM Cluster c WHERE c.galId = :galId"),
    @NamedQuery(name = "Cluster.findByName", query = "SELECT c FROM Cluster c WHERE c.name = :name")})
public class Cluster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Disc_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date discdate;
    @Basic(optional = false)
    @Lob
    @Column(name = "Name")
    private String name;
    @Lob
    @Column(name = "Observations")
    private String observations;
    @Lob
    @Column(name = "ImgUrl")
    private String imgUrl;
    @JoinColumn(name = "GalId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Galaxy galId;
    @JoinColumn(name = "Discoverer", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Users discoverer;

    /**
     *
     */
    public Cluster() {
    }

    /**
     *
     * @param id
     */
    public Cluster(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param number
     * @param discdate
     * @param name
     * @param imgUrl
     */
    public Cluster(Integer id, Date discdate, String name, String imgUrl) {
        this.id = id;
        this.discdate = discdate;
        this.name = name;
        this.imgUrl = imgUrl;
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
    public Date getDiscdate() {
        return discdate;
    }

    /**
     *
     * @param discdate
     */
    public void setDiscdate(Date discdate) {
        this.discdate = discdate;
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
     * @return
     */
    public String getObservations() {
        return observations;
    }

    /**
     *
     * @param observations
     */
    public void setObservations(String observations) {
        this.observations = observations;
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
    public Galaxy getGalId() {
        return galId;
    }

    /**
     *
     * @param galId
     */
    public void setGalId(Galaxy galId) {
        this.galId = galId;
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
        if (!(object instanceof Cluster)) {
            return false;
        }
        Cluster other = (Cluster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SEdbLib.Cluster[ id=" + id + " ]";
    }
    
    /**
     *
     * @return
     */
    public String genText()
    {String text;
     text = "Name : "+this.getName()+"\n";
     text += "Discoverer : "+this.getDiscoverer().getUser()+"\n";
     text += "Obs : "+this.getObservations()+"\n";
     text += "ImgUrl : "+this.getImgUrl()+"\n\n";
     return text; 
    }
    
}
