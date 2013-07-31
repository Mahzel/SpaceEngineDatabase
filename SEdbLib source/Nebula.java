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
@Table(name = "nebula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nebula.findAll", query = "SELECT n FROM Nebula n"),
    @NamedQuery(name = "Nebula.findById", query = "SELECT n FROM Nebula n WHERE n.id = :id"),
    @NamedQuery(name = "Nebula.findByDiscdate", query = "SELECT n FROM Nebula n WHERE n.discdate = :discdate"),
    @NamedQuery(name = "Nebula.findByParGal", query = "SELECT n FROM Nebula n WHERE n.parGal = :parGal"),
    @NamedQuery(name = "Nebula.findByName", query = "SELECT n FROM Nebula n WHERE n.name = :name")})
public class Nebula implements Serializable {
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
    @JoinColumn(name = "ParGal", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Galaxy parGal;
    @JoinColumn(name = "Discoverer", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Users discoverer;

    /**
     *
     */
    public Nebula() {
    }

    /**
     *
     * @param id
     */
    public Nebula(Integer id) {
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
    public Nebula(Integer id, Date discdate, String name, String imgUrl) {
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
    public Galaxy getParGal() {
        return parGal;
    }

    /**
     *
     * @param parGal
     */
    public void setParGal(Galaxy parGal) {
        this.parGal = parGal;
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
        if (!(object instanceof Nebula)) {
            return false;
        }
        Nebula other = (Nebula) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SEdbLib.Nebula[ id=" + id + " ]";
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
