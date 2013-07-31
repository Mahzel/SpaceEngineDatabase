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
    String[] fStrings;
    String cname = "RC ";
    String nname = "RN ";
    String sname = "RS ";
    
    public GalaxyChildrenFactory(Galaxy pgal)
    {this.pgal = pgal;
    this.fFlags = null;}
    
    public GalaxyChildrenFactory(Galaxy pgal, boolean[] f, String[] s)
    {this.pgal = pgal;
    this.fFlags = f;
    this.fStrings = s;
    if(!fStrings[0].equals(""))
            {   String[] Arr = fStrings[0].split("[ -]+");
                for(int i = 1; i < Arr.length && i < 10; i++){
                       if(i<5 && i < Arr.length-1){nname = nname+Arr[i]+"-";}
                       else if(i==5){nname = nname+Arr[i];}
                       if(i<5 && i < Arr.length-1){cname = cname+Arr[i]+"-";}
                       else if(i==5){cname = cname+Arr[i];}
                       if(i<8 && i < Arr.length-1){sname = sname+Arr[i]+"-";}
                       else if(i==8){sname = sname+Arr[i];}
                 }
                cname = cname + "%";
                nname = nname + "%";
                sname = sname + "%";}
    System.out.println(cname +" : "+nname+" - "+sname);
    }
    
    @Override
    protected boolean createKeys(List toPopulate) {
        int i = 0;
        EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query q;
        if(cname.equals("RC ")){
        q = em.createNamedQuery("Cluster.findByGalId").setParameter("galId",pgal);}
        else{q = em.createQuery("SELECT c FROM Cluster c WHERE c.name LIKE :pname").setParameter("pname", cname);}
        List<Cluster> resc = q.getResultList();
        if(nname.equals("RN ")){
        q = em.createNamedQuery("Nebula.findByParGal").setParameter("parGal", pgal);}
        else{q = em.createQuery("SELECT n FROM Nebula n WHERE n.name LIKE :pname").setParameter("pname", cname);}
        List<Nebula> resn = q.getResultList();
        if(sname.equals("RS ")){
        q = em.createNamedQuery("Star.findByGal").setParameter("pId", pgal.getId());}
        else{q = em.createQuery("SELECT s FROM Star s WHERE s.name LIKE :pname").setParameter("pname", sname);}
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
        em.close();
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
        else{if(!fStrings[1].equals("")){
            if(neb.getDiscoverer().getUser().equals(fStrings[1])){ 
            result = new AbstractNode(
            Children.create(new NebulaChildrenFactory(neb, fFlags, fStrings), true),
            Lookups.singleton(neb));}
            else return null;}
        else{result = new AbstractNode(
            Children.create(new NebulaChildrenFactory(neb, fFlags, fStrings), true),
            Lookups.singleton(neb));}}
    result.setDisplayName(neb.getName());}
    if(cname.equals("Cluster")){
        Cluster clu = (Cluster)key;
        if(fFlags == null){
        result = new AbstractNode(
            Children.create(new ClusterChildrenFactory(clu), true),
            Lookups.singleton(clu));}
        else{if(!fStrings[1].equals("")){
            if(clu.getDiscoverer().getUser().equals(fStrings[1])){ 
            result = new AbstractNode(
            Children.create(new ClusterChildrenFactory(clu, fFlags, fStrings), true),
            Lookups.singleton(clu));}
            else return null;}
        else{result = new AbstractNode(
            Children.create(new ClusterChildrenFactory(clu, fFlags, fStrings), true),
            Lookups.singleton(clu));}}
    result.setDisplayName(clu.getName());}
    if(cname.equals("Star")){
        Star sta = (Star)key;
         if(fFlags == null){
        result = new AbstractNode(
            Children.create(new StarChildrenFactory(sta), true),
            Lookups.singleton(sta));}
        else{if(!fStrings[1].equals("")){
            if(sta.getDiscoverer().getUser().equals(fStrings[1])){ 
            result = new AbstractNode(
            Children.create(new StarChildrenFactory(sta, fFlags, fStrings), true),
            Lookups.singleton(sta));}
            else return null;}
        else{result = new AbstractNode(
            Children.create(new StarChildrenFactory(sta, fFlags, fStrings), true),
            Lookups.singleton(sta));}}
    result.setDisplayName(sta.getName());}
    return result;}

    
}
