/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.SpaceEngineDatabase.ViewerModule;

import SEdbLib.Planet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Mahzel
 */
public class PlanetChildrenFactory extends ChildFactory<Planet>{
    Planet pPlanet;
    boolean[] fFlags;
    
    public PlanetChildrenFactory(Planet pla) {
        this.pPlanet = pla;
    }
    
    public PlanetChildrenFactory(Planet pla, boolean[] f) {
        this.pPlanet = pla;
        this.fFlags = f;
    }
    
    @Override
    protected boolean createKeys(List toPopulate) {
        EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query q = em.createNamedQuery("Planet.findByPPID").setParameter("pid",pPlanet.getId());
        List<Planet> resP = q.getResultList();
        try{if(fFlags[3]){
        toPopulate.addAll(resP);}
        else{em.close();
        return true;}}
        catch(NullPointerException e)
        {toPopulate.addAll(resP);}
        em.close();
        return true;
    }

    @Override
    protected Node createNodeForKey(Planet key) {
        Node result = new AbstractNode(
            Children.LEAF,
            Lookups.singleton(key));
    result.setDisplayName(key.getName());
    return result;}    
}
