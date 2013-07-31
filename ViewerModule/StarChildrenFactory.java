/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.SpaceEngineDatabase.ViewerModule;

import SEdbLib.Planet;
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
public class StarChildrenFactory extends ChildFactory<Object>{
    Star pStar;
    boolean[] fFlags;
    String[] fStrings;
    
    public StarChildrenFactory(Star sta) {
        this.pStar = sta;
    }
    
    public StarChildrenFactory(Star sta, boolean[] f, String[] s) {
        this.pStar = sta;
        this.fFlags = f;
        this.fStrings = s;
    }
    
    @Override
    protected boolean createKeys(List toPopulate) {
        EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query q = em.createNamedQuery("Planet.findBySPID").setParameter("pid",pStar.getId());
        List<Planet> resP = q.getResultList();
        Query qs = em.createNamedQuery("Star.findByBary").setParameter("pId",pStar.getId());
        List<Star> resS = qs.getResultList();
        try{for(int i = 0; i< resP.size(); i++){
            if(fFlags[2] && !(resP.get(i).getIsAsteroid()))
            {toPopulate.add(resP.get(i));}
            if(fFlags[3] && resP.get(i).getIsAsteroid())
            {toPopulate.add(resP.get(i));}}}
        catch(NullPointerException e)
        {toPopulate.addAll(resP);}
        toPopulate.addAll(resS);
        em.close();
        return true;
    }

    @Override
    protected Node createNodeForKey(Object key) {
    Node result = null;
    String cname = key.getClass().getSimpleName();
    if(cname.equals("Planet")){
        Planet pla = (Planet)key;
        if(fFlags == null){
        result = new AbstractNode(
            Children.create(new PlanetChildrenFactory(pla), true),
            Lookups.singleton(pla));}
        else{result = new AbstractNode(
            Children.create(new PlanetChildrenFactory(pla, fFlags), true),
            Lookups.singleton(pla));}
    result.setDisplayName(pla.getName());}
    if(cname.equals("Star")){
        Star sta = (Star)key;
        if(fFlags == null){
            result = new AbstractNode(
            Children.create(new StarChildrenFactory(sta), true),
            Lookups.singleton(key));}
        else{result = new AbstractNode(
            Children.create(new StarChildrenFactory(sta, fFlags, fStrings), true),
            Lookups.singleton(key));}
    result.setDisplayName(sta.getName());}
    return result;}
    
}