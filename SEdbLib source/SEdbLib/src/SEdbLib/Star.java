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
@Table(name = "star")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Star.findAll", query = "SELECT s FROM Star s"),
    @NamedQuery(name = "Star.findById", query = "SELECT s FROM Star s WHERE s.id = :id"),
    @NamedQuery(name = "Star.findByDate", query = "SELECT s FROM Star s WHERE s.date = :date"),
    @NamedQuery(name = "Star.findByGal", query = "SELECT s FROM Star s WHERE s.parentId =:pId AND s.isInClus = FALSE AND s.isInNeb = FALSE"),
    @NamedQuery(name = "Star.findByClus", query = "SELECT s FROM Star s WHERE s.parentId =:pId AND s.isInClus = TRUE"),
    @NamedQuery(name = "Star.findByNeb", query = "SELECT s FROM Star s WHERE s.parentId =:pId AND s.isInNeb = TRUE"),
    @NamedQuery(name = "Star.findByBary", query = "SELECT s FROM Star s WHERE s.parentId =:pId AND s.baryLetter IS NOT NULL"),
    @NamedQuery(name = "Star.findByName", query = "SELECT s FROM Star s WHERE s.name = :name")})
public class Star implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Bary_Letter")
    private String baryLetter;
    @Basic(optional = false)
    @Column(name = "IsInNeb")
    private boolean isInNeb;
    @Basic(optional = false)
    @Column(name = "IsInClus")
    private boolean isInClus;
    @Basic(optional = false)
    @Column(name = "IsBary")
    private boolean isBary;
    @Basic(optional = false)
    @Column(name = "ParentId")
    private int parentId;
    @Basic(optional = false)
    @Lob
    @Column(name = "Name")
    private String name;
    @Lob
    @Column(name = "Observations")
    private String observations;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Lob
    @Column(name = "ImgUrl")
    private String imgUrl;
    @JoinColumn(name = "Discoverer", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Users discoverer;

    /**
     *
     */
    public Star() {
    }

    /**
     *
     * @param id
     */
    public Star(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param baryLetter 
     * @param isInNeb
     * @param isInClus 
     * @param isBary 
     * @param parentId
     * @param name
     * @param observations
     * @param date
     * @param imgUrl
     */
    public Star(Integer id, String baryLetter, boolean isInNeb, boolean isInClus, boolean isBary, int parentId, String name, String observations, Date date, String imgUrl) {
        this.id = id;
        this.isBary = isBary;
        this.baryLetter = baryLetter;
        this.isInNeb = isInNeb;
        this.isInClus = isInClus;
        this.parentId = parentId;
        this.name = name;
        this.observations = observations;
        this.date = date;
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
    public String getBaryLetter() {
        return baryLetter;
    }

    /**
     *
     * @param baryLetter
     */
    public void setBaryLetter(String baryLetter) {
        this.baryLetter = baryLetter;
    }

    /**
     *
     * @return
     */
    public boolean getIsInNeb() {
        return isInNeb;
    }

    /**
     *
     * @param isInNeb
     */
    public void setIsInNeb(boolean isInNeb) {
        this.isInNeb = isInNeb;
    }
     /**
     *
     * @return
     */
    public boolean getIsInClus() {
        return isInClus;
    }

    /**
     *
     * @param isInClus
     */
    public void setIsInClus(boolean isInClus) {
        this.isInClus = isInClus;
    }
    
     /**
     *
     * @return
     */
    public boolean getIsBary() {
        return isBary;
    }

    /**
     *
     * @param isBary 
     */
    public void setIsBary(boolean isBary) {
        this.isBary = isBary;
    }

    /**
     *
     * @return
     */
    public int getParentId() {
        return parentId;
    }

    /**
     *
     * @param parentId
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
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
        if (!(object instanceof Star)) {
            return false;
        }
        Star other = (Star) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SEdbLib.Star[ id=" + id + " ]";
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
     if(this.getBaryLetter() != null)
        text += "Bary Letter : "+this.getBaryLetter()+"\n";
     if(this.getIsBary())
         text += "Is Barycenter. \n";
     text += "ImgUrl : "+this.getImgUrl()+"\n\n";
     return text; 
    }
    
}
