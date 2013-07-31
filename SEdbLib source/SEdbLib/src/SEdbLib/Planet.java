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
@Table(name = "planet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planet.findAll", query = "SELECT p FROM Planet p"),
    @NamedQuery(name = "Planet.findById", query = "SELECT p FROM Planet p WHERE p.id = :id"),
    @NamedQuery(name = "Planet.findByDate", query = "SELECT p FROM Planet p WHERE p.date = :date"),
    @NamedQuery(name = "Planet.findBySPID", query = "SELECT p FROM Planet p WHERE p.parentid = :pid AND p.ismoon = FALSE"),
    @NamedQuery(name = "Planet.findByPPID", query = "SELECT p FROM Planet p WHERE p.parentid = :pid AND p.ismoon = TRUE"),
    @NamedQuery(name = "Planet.findByIsmoon", query = "SELECT p FROM Planet p WHERE p.ismoon = :ismoon"),
    @NamedQuery(name = "Planet.findByName", query = "SELECT p FROM Planet p WHERE p.name = :name")})
public class Planet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Parent_id")
    private int parentid;
    @Basic(optional = false)
    @Column(name = "Around_Bary")
    private boolean aroundBary;
    @Basic(optional = false)
    @Lob
    @Column(name = "Name")
    private String name;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Lob
    @Column(name = "Observations")
    private String observations;
    @Basic(optional = false)
    @Column(name = "Is_moon")
    private boolean ismoon;
     @Basic(optional = false)
    @Column(name = "IsAsteroid")
    private boolean isAsteroid;
    @Lob
    @Column(name = "ImgUrl")
    private String imgUrl;
    @JoinColumn(name = "Discoverer", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Users discoverer;

    /**
     *
     */
    public Planet() {
    }

    /**
     *
     * @param id
     */
    public Planet(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param number
     * @param parentid
     * @param aroundBary
     * @param name
     * @param date
     * @param observations
     * @param ismoon
     * @param isAsteroid 
     * @param imgUrl
     */
    public Planet(Integer id, int parentid, boolean aroundBary, String name, Date date, String observations, boolean ismoon, boolean isAsteroid, String imgUrl) {
        this.id = id;
        this.parentid = parentid;
        this.aroundBary = aroundBary;
        this.name = name;
        this.date = date;
        this.observations = observations;
        this.ismoon = ismoon;
        this.isAsteroid = isAsteroid;
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
    public int getParentid() {
        return parentid;
    }

    /**
     *
     * @param parentid
     */
    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    /**
     *
     * @return
     */
    public boolean getAroundBary() {
        return aroundBary;
    }

    /**
     *
     * @param aroundBary
     */
    public void setAroundBary(boolean aroundBary) {
        this.aroundBary = aroundBary;
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
    public boolean getIsmoon() {
        return ismoon;
    }

    /**
     *
     * @param ismoon
     */
    public void setIsmoon(boolean ismoon) {
        this.ismoon = ismoon;
    }
    /**
     *
     * @return
     */
    public boolean getIsAsteroid() {
        return isAsteroid;
    }

    /**
     *
     * @param isAsteroid 
     */
    public void setIsAsteroid(boolean isAsteroid) {
        this.isAsteroid = isAsteroid;
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
        if (!(object instanceof Planet)) {
            return false;
        }
        Planet other = (Planet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SEdbLib.Planet[ id=" + id + " ]";
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
     if(getAroundBary())
         text += "Around barycenter. \n";
     if(getIsmoon())
         text += "Is moon. \n";
     if(getIsAsteroid())
         text += "Is Asteroid. \n";
     text += "ImgUrl : "+this.getImgUrl()+"\n\n";
     return text;
    
    }
    
    
}
