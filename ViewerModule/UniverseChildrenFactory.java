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
    private String[] fStrings;
    
    public UniverseChildrenFactory(boolean[] f, String[] s)
    {this.fFlags = f;
    this.fStrings = s;}
    
    public UniverseChildrenFactory()
    {this.fFlags = null;
    this.fStrings = null;}
    
    @Override
    protected boolean createKeys(List toPopulate) {
        EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query q;
        if(fStrings != null)
        {if(!fStrings[0].equals(""))
            {   String[] Arr = fStrings[0].split("[ -]+");
                String pname = "RG ";
                for(int i = 1; i < Arr.length && i < 5; i++){
                        pname = pname+Arr[i];
                        if(i!=4 && i!=Arr.length-1){pname = pname+"-";}
                 }
                pname = pname + "%";
                System.out.println(pname + " Inside name filter");
                q = em.createQuery("SELECT g FROM Galaxy g WHERE g.name LIKE :pname").setParameter("pname", pname);
            }
            else{q = em.createNamedQuery("Galaxy.findAll");}
        }
        else{q = em.createNamedQuery("Galaxy.findAll");}
        galArr = q.getResultList();
        toPopulate.addAll(galArr);
        em.close();
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Galaxy key) {
        Node result = null;
        if(fFlags == null)
        {   result = new AbstractNode(
            Children.create(new GalaxyChildrenFactory(key), true),
            Lookups.singleton(key));
            result.setDisplayName(key.getName());
            return result;
        }
        else
        {   if(!fStrings[1].equals("")){
                   System.out.println(key.getDiscoverer().getUser() + " Inside author filter");
                if(key.getDiscoverer().getUser().equals(fStrings[1]))
                {
                    result = new AbstractNode(
                    Children.create(new GalaxyChildrenFactory(key, fFlags, fStrings), true),
                    Lookups.singleton(key));
                    result.setDisplayName(key.getName());
                    return result;}
            }
        else{result = new AbstractNode(
            Children.create(new GalaxyChildrenFactory(key, fFlags, fStrings), true),
            Lookups.singleton(key));
            result.setDisplayName(key.getName());
            return result;}
        }
    return result;
}
    
}
