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
    String[] fStrings;
    String sname;
    
    public NebulaChildrenFactory(Nebula neb) {
        this.pNeb = neb;
        sname = "RSN ";
    }
    
    public NebulaChildrenFactory(Nebula neb, boolean[] f, String[] s) {
        this.pNeb = neb;
        this.fFlags = f;
        this.fStrings =s;
        sname = "RSN ";
        if(!fStrings[0].equals(""))
            {   String[] Arr = fStrings[0].split("[ -]+");
                for(int i = 1; i < Arr.length && i < 7; i++){
                       if(i<6 && i < Arr.length-1){sname = sname+Arr[i]+"-";}
                       else if(i==6){sname = sname+Arr[i];}
                 }
                sname = sname + "%";}
    System.out.println(sname);
    }
    
    @Override
    protected boolean createKeys(List toPopulate) {
        EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query q;
        if(sname=="RSN "){
        q = em.createNamedQuery("Star.findByNeb").setParameter("pId",pNeb.getId());}
        else{
        q = em.createQuery("SELECT s FROM Star s WHERE s.name LIKE :pname").setParameter("pname", sname);}
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
        else if(fStrings[1].equals("")){
            result = new AbstractNode(
            Children.create(new StarChildrenFactory(key, fFlags, fStrings), true),
            Lookups.singleton(key));}
        else if(key.getDiscoverer().getUser().equals(fStrings[1]))
        {result = new AbstractNode(
            Children.create(new StarChildrenFactory(key, fFlags, fStrings), true),
            Lookups.singleton(key));}
        else return null;
    result.setDisplayName(key.getName());
    return result;}
    
}
