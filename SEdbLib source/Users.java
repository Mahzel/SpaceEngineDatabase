/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SEdbLib;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mahzel
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByNick", query = "SELECT u FROM Users u WHERE u.user = :nick")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "User")
    private String user;
    @Basic(optional = false)
    @Lob
    @Column(name = "Pass")
    private String pass;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discoverer")
    private Collection<Nebula> nebulaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discoverer")
    private Collection<Planet> planetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discoverer")
    private Collection<Star> starCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discoverer")
    private Collection<Cluster> clusterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discoverer")
    private Collection<Galaxy> galaxyCollection;

    /**
     *
     */
    public Users() {
    }

    /**
     *
     * @param id
     */
    public Users(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param user
     * @param pass
     */
    public Users(Integer id, String user, String pass) {
        this.id = id;
        this.user = user;
        this.pass = pass;
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
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public String getPass() {
        return pass;
    }

    /**
     *
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = pass;
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
    public Collection<Planet> getPlanetCollection() {
        return planetCollection;
    }

    /**
     *
     * @param planetCollection
     */
    public void setPlanetCollection(Collection<Planet> planetCollection) {
        this.planetCollection = planetCollection;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Star> getStarCollection() {
        return starCollection;
    }

    /**
     *
     * @param starCollection
     */
    public void setStarCollection(Collection<Star> starCollection) {
        this.starCollection = starCollection;
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
    @XmlTransient
    public Collection<Galaxy> getGalaxyCollection() {
        return galaxyCollection;
    }

    /**
     *
     * @param galaxyCollection
     */
    public void setGalaxyCollection(Collection<Galaxy> galaxyCollection) {
        this.galaxyCollection = galaxyCollection;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SEdbLib.Users[ id=" + id + " ]";
    }
    
}
