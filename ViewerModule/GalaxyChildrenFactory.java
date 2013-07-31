/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.SpaceEngineDatabase.ViewerModule;

import SEdbLib.Cluster;
import SEdbLib.Galaxy;
import SEdbLib.Nebula;
import SEdbLib.Star;
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
public class GalaxyChildrenFactory extends ChildFactory<Object>{
    Galaxy pgal;
    boolean[] fFlags;
    
    public GalaxyChildrenFactory(Galaxy pgal)
    {this.pgal = pgal;
    this.fFlags = null;}
    
    public GalaxyChildrenFactory(Galaxy pgal, boolean[] f)
    {this.pgal = pgal;
    this.fFlags = f;}
    
    @Override
    protected boolean createKeys(List toPopulate) {
        int i = 0;
        EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query q = em.createNamedQuery("Cluster.findByGalId").setParameter("galId",pgal);
        List<Cluster> resc = q.getResultList();
        q = em.createNamedQuery("Nebula.findByParGal").setParameter("parGal", pgal);
        List<Nebula> resn = q.getResultList();
        q = em.createNamedQuery("Star.findByGal").setParameter("pId", pgal.getId());
        List<Star> resS = q.getResultList();
        try{
            if(fFlags[0])
                toPopulate.addAll(resn);
            if(fFlags[1])
                toPopulate.addAll(resc);
        }
        catch(NullPointerException e)
        {toPopulate.addAll(resn);
        toPopulate.addAll(resc);}
        finally{toPopulate.addAll(resS);}
        return true;
    }

    @Override
    protected Node createNodeForKey(Object key) {
    Node result = null;
    String cname = key.getClass().getSimpleName();
    if(cname.equals("Nebula")){
        Nebula neb = (Nebula)key;
         if(fFlags == null){
        result = new AbstractNode(
            Children.create(new NebulaChildrenFactory(neb), true),
            Lookups.singleton(neb));}
        else{result = new AbstractNode(
            Children.create(new NebulaChildrenFactory(neb, fFlags), true),
            Lookups.singleton(neb));}
    result.setDisplayName(neb.getName());}
    if(cname.equals("Cluster")){
        Cluster clu = (Cluster)key;
        if(fFlags == null){
        result = new AbstractNode(
            Children.create(new ClusterChildrenFactory(clu), true),
            Lookups.singleton(clu));}
        else{result = new AbstractNode(
            Children.create(new ClusterChildrenFactory(clu, fFlags), true),
            Lookups.singleton(clu));}
    result.setDisplayName(clu.getName());}
    if(cname.equals("Star")){
        Star sta = (Star)key;
        if(fFlags == null){
        result = new AbstractNode(
            Children.create(new StarChildrenFactory(sta), true),
            Lookups.singleton(sta));}
        else{
            result = new AbstractNode(
            Children.create(new StarChildrenFactory(sta, fFlags), true),
            Lookups.singleton(sta));}
    result.setDisplayName(sta.getName());}
    return result;}

    
}
