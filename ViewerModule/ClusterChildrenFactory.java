/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.SpaceEngineDatabase.ViewerModule;

import SEdbLib.Cluster;
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
public class ClusterChildrenFactory extends ChildFactory<Star>{
    Cluster pClus;
    boolean[] fFlags;
    String[] fStrings;
    String sname;
    
    public ClusterChildrenFactory(Cluster clu) {
        this.pClus = clu;
        sname = "RSC ";
    }
    
    public ClusterChildrenFactory(Cluster clu, boolean[] f, String[] s) {
        this.pClus = clu;
        this.fFlags = f;
        this.fStrings = s;
        sname = "RSC ";
        if(!fStrings[0].equals(""))
            {   String[] Arr = fStrings[0].split("[ -]+");
                for(int i = 1; i < Arr.length && i < 10; i++){
                       if(i<9 && i < Arr.length-1){sname = sname+Arr[i]+"-";}
                       else if(i==9){sname = sname+Arr[i];}
                 }
                sname = sname + "%";}
    System.out.println(sname);
    }
    
    @Override
    protected boolean createKeys(List toPopulate) {
        EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query q;
        if(sname.equals("RSC ")){
        q = em.createNamedQuery("Star.findByClus").setParameter("pId",pClus.getId());}
        else{q = em.createQuery("SELECT s FROM Star s WHERE s.name LIKE :pname").setParameter("pname", sname);}
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
