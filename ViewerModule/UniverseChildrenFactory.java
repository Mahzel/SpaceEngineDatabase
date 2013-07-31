/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.SpaceEngineDatabase.ViewerModule;

import SEdbLib.Galaxy;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Mahzel
 */
public class UniverseChildrenFactory extends ChildFactory<Galaxy>{
    private List<Galaxy> galArr;
    private boolean[] fFlags;
    
    public UniverseChildrenFactory(boolean[] f)
    {this.fFlags = f;}
    
    public UniverseChildrenFactory()
    {this.fFlags = null;}
    
    @Override
    protected boolean createKeys(List toPopulate) {
        EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query q = em.createNamedQuery("Galaxy.findAll");
        galArr = q.getResultList();
        toPopulate.addAll(galArr);
        em.close();
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Galaxy key) {
        Node result;
        if(fFlags == null)
        {   result = new AbstractNode(
            Children.create(new GalaxyChildrenFactory(key), true),
            Lookups.singleton(key));}
        else
        {   result = new AbstractNode(
            Children.create(new GalaxyChildrenFactory(key, fFlags), true),
            Lookups.singleton(key));}
    result.setDisplayName(key.getName());
    return result;
}
    
}
