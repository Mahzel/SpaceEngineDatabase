/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.SpaceEngineDatabase.ViewerModule;

import SEdbLib.Cluster;
import SEdbLib.Nebula;
import SEdbLib.Star;
import java.util.ArrayList;
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
public class NebulaChildrenFactory extends ChildFactory<Star>{
    Nebula pNeb;
    boolean[] fFlags;
    
    public NebulaChildrenFactory(Nebula neb) {
        this.pNeb = neb;
    }
    
    public NebulaChildrenFactory(Nebula neb, boolean[] f) {
        this.pNeb = neb;
        this.fFlags = f;
    }
    
    @Override
    protected boolean createKeys(List toPopulate) {
        EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query q = em.createNamedQuery("Star.findByNeb").setParameter("pId",pNeb.getId());
        List<Star> sList = q.getResultList();
        toPopulate.addAll(sList);
        em.close();
        return true;
    }

    @Override
    protected Node createNodeForKey(Star key) {
        Node result;
        if(fFlags == null){
            result = new AbstractNode(
            Children.create(new StarChildrenFactory(key), true),
            Lookups.singleton(key));}
        else{result = new AbstractNode(
            Children.create(new StarChildrenFactory(key, fFlags), true),
            Lookups.singleton(key));}
    result.setDisplayName(key.getName());
    return result;}
    
}
