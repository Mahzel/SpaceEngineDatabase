/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SEdbLib;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Mahzel
 */
public class InsertManager {
    //Variables - Objets
    /**
     *
     */
    public Galaxy g = null;
    /**
     *
     */
    public Cluster c = null;
    /**
     *
     */
    public Nebula n = null;
    /**
     *
     */
    public Planet p = null;
    /**
     *
     */
    public Star s = null;
    public Star b = null;
    /**
     *
     */
    public Users u = null;
    /**
     *
     */
    public Planet m = null;
    //Variables de construction d'objet
    /**
     *
     */
    public String text = new String();
    /**
     *
     */
    public String name;
    /**
     *
     */
    public String obs;
    /**
     *
     */
    public String type;
    /**
     *
     */
    public String imgUrl;
    /**
     *
     */
    public String author;
    /**
     *
     */
    public String[] split;
    Map gFlags;
    
    //getters - setters
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     *
     * @return
     */
    public String getObs() {
        return obs;
    }
    /**
     *
     * @param obs
     */
    public void setObs(String obs) {
        this.obs = obs;
    }
    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }
    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     *
     * @return
     */
    public String getImgUrl() {
        return imgUrl;
    }
    /**
     *
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    /**
     *
     * @return
     */
    public String getAuthor() {
        return author;
    }
    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     *
     * @return
     */
    public String[] getSplit() {
        return split;
    }
    /**
     *
     * @param split
     */
    public void setSplit(String[] split) {
        this.split = split;
    }
    
    
    //Build manager - Build object depending on type
    /**
     *
     * @param n
     * @param o
     * @param t
     * @param i
     * @param a
     * @param s
     * @param flags  
     */
    public void BuildManager(String n, String o, String t, String i, String a, String[] s, Map flags)
    {   gFlags = flags;
        Boolean debug = false;
        this.name = n;
        if(debug)System.out.println("Debug : Name :"+this.name);
        this.obs = o;
        if(debug)System.out.println("Debug : obs :"+this.obs);
        this.type = t;
        if(debug)System.out.println("Debug : type :"+this.type);
        this.imgUrl = i;
        if(debug)System.out.println("Debug : ImgUrl :"+this.imgUrl);
        this.author = a;
        if(debug)System.out.println("Debug : Author :"+this.author);
        this.split = s;
        this.u = UserManager.QueryByName(this.author);
        if(debug)System.out.println("Debug : User check  :"+u.getUser());
        switch(type){
            case "Galaxy":pGal();break;
            case "Cluster":pCluster();break;
            case "Nebula":pNebula();break;
            case "Star":pStar();break;
            case "Body":
                if((boolean)gFlags.get("isMoon"))
                    pMoon();
                else 
                    pBody();
                break;
        }
    }
    
    /**
     *
     */
    public void Update()
    {   boolean debug = false;
        if(g!=null)
            {if(debug){System.out.println("Inserting galaxy");}
             EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            em.getTransaction().begin();
            em.merge(g);
            em.getTransaction().commit();
            em.close();
            g = null;
        }
        if(c!=null)
            {if(debug){System.out.println("Inserting cluster");}
             EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
            em.close();
            c = null;
        }
        if(n!=null)
            {if(debug){System.out.println("Inserting nebula");}
            EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            em.getTransaction().begin();
            em.merge(n);
            em.getTransaction().commit();
            em.close();
            n = null;
        }
        if(s!=null)
            {if(debug){System.out.println("Inserting star");}
            EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
            em.close();
            s = null;
        }
        if(p!=null)
        {if(debug){System.out.println("Inserting planet");}
            EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            em.close();
            p = null;
        }
    }
    
    
    
    /**
     *
     */
    public void Insertion ()
    {   boolean debug = false;
        if(g!=null)
            {if(debug){System.out.println("Inserting galaxy");}
             EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            em.getTransaction().begin();
            em.persist(g);
            em.getTransaction().commit();
            em.close();
            g = queryGalByName(g.getName());
        }
        if(c!=null)
            {if(debug){System.out.println("Inserting cluster");}
             EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            if(c.getGalId()==null)
                c.setGalId(g);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            em.close();
            c = queryClusByName(c.getName());
        }
        if(n!=null)
            {if(debug){System.out.println("Inserting nebula");}
            EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            if(n.getParGal()==null)
                n.setParGal(g);
            em.getTransaction().begin();
            em.persist(n);
            em.getTransaction().commit();
            em.close();
            n = queryNebByName(n.getName());
        }
        if(b!=null)
            {if(debug){System.out.println("Inserting barycenter");}
            EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            if(b.getParentId()==0)
            {if(b.getIsInClus()){b.setParentId(c.getId());}
             else if(b.getIsInNeb()){b.setParentId(n.getId());}
             else {b.setParentId(g.getId());}
            }
            em.getTransaction().begin();
            em.persist(b);
            em.getTransaction().commit();
            em.close();
            b = queryStarByName(b.getName());
        }
        if(s!=null)
            {if(debug){System.out.println("Inserting star");}
            EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            if(s.getParentId()==0)
            {if(s.getIsInClus()){s.setParentId(c.getId());}
             else if(s.getIsInNeb()){s.setParentId(n.getId());}
             else if (s.getBaryLetter()!= null){s.setParentId(b.getId());}
             else {s.setParentId(g.getId());}
            }
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            em.close();
            s = queryStarByName(s.getName());
        }
        if(p!=null)
        {if(debug){System.out.println("Inserting planet");}
            EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            if(p.getParentid()==0)
                p.setParentid(s.getId());
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            em.close();
            p = queryBodyByName(p.getName());
        }
        if(m!=null)
        {if(debug){System.out.println("Inserting moon");}
            EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
            if(m.getParentid()==0)
                m.setParentid(p.getId());
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
            em.close();
            m = queryBodyByName(m.getName());
        }
        g = null;c = null;n = null;s=null;p=null;m=null;
    }
        
    
    /**
     *
     * @param name
     * @return
     */
    public static Galaxy queryGalByName(String name)
    {   EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query query = em.createNamedQuery("Galaxy.findByName").setParameter("name", name).setMaxResults(1);
        List<Galaxy> result = query.getResultList();
        try{result.get(0);}
        catch(java.lang.ArrayIndexOutOfBoundsException exc)
        {return null;}
        Galaxy g = result.get(0);
        return g;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public static Nebula queryNebByName(String name)
    {   EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query query = em.createNamedQuery("Nebula.findByName").setParameter("name", name).setMaxResults(1);
        List<Nebula> result = query.getResultList();
        try{result.get(0);}
        catch(java.lang.ArrayIndexOutOfBoundsException exc)
        {return null;}
        Nebula n = result.get(0);
        return n;
    }
    
     /**
     *
     * @param name
     * @return
     */
    public static Cluster queryClusByName(String name)
    {   EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query query = em.createNamedQuery("Cluster.findByName").setParameter("name", name).setMaxResults(1);
        List<Cluster> result = query.getResultList();
        try{result.get(0);}
        catch(java.lang.ArrayIndexOutOfBoundsException exc)
        {return null;}
        Cluster c = result.get(0);
        return c;
    }
     
     /**
     *
     * @param name
     * @return
     */
    public static Star queryStarByName(String name)
    {   EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query query = em.createNamedQuery("Star.findByName").setParameter("name", name).setMaxResults(1);
        List<Star> result = query.getResultList();
        try{result.get(0);}
        catch(java.lang.ArrayIndexOutOfBoundsException exc)
        {return null;}
        Star s = result.get(0);
        return s;
    }
     
     /**
     *
     * @param name
     * @return
     */
    public static Planet queryBodyByName(String name)
    {   EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query query = em.createNamedQuery("Planet.findByName").setParameter("name", name).setMaxResults(1);
        List<Planet> result = query.getResultList();
        try{result.get(0);}
        catch(java.lang.ArrayIndexOutOfBoundsException exc)
        {return null;}
        Planet p = result.get(0);
        return p;
    }
     
    /**
     *
     */
    public void pGal(){
    g=queryGalByName(name);
    if(g==null){
        g = new Galaxy();
        g.setDiscoverer(u);
        g.setSupStack(Integer.valueOf(this.split[1]));
        g.setStack(Integer.valueOf(this.split[2]));
        g.setLocalGroup(Integer.valueOf(this.split[3]));
        g.setNum(Integer.valueOf(this.split[4]));
        g.setName();
        g.setImgUrl(this.imgUrl);
        g.setObserv(this.obs);
        this.text = text+g.genText();}
    else
        this.text = "Object already exists, discard";
    }
    
    /**
     *
     */
    public void pCluster(){
    c = queryClusByName(name);
    if(c==null){
        c = new Cluster();
        c.setDiscoverer(u);
        c.setGalId(null);
        Galaxy gal = queryGalByName("RG "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]) ;
        if(gal == null)
        {       String tempUrl = this.imgUrl;String tempObs = this.obs;
                this.imgUrl = " ";this.obs = "Automatically generated";
                pGal();
                this.imgUrl = tempUrl;this.obs = tempObs;}
        else {c.setGalId(gal);}
        c.setName(name);
        c.setImgUrl(imgUrl);
        c.setObservations(obs);
        this.text = text+c.genText();}
    else
        this.text = "Object already exists, discard";
    }
    
    /**
     *
     */
    public void pNebula(){
        n = queryNebByName(name);
        if(n==null){
            n = new Nebula();
            n.setParGal(null);
            n.setDiscoverer(u);
            Galaxy gal = queryGalByName("RG "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]) ;
            if(gal == null)
            {       String tempUrl = this.imgUrl;String tempObs = this.obs;
                    this.imgUrl = " ";this.obs = "Automatically generated";
                    pGal();
                    this.imgUrl = tempUrl;this.obs = tempObs;
            }
            else{n.setParGal(gal);}
            n.setName(name);
            n.setImgUrl(imgUrl);
            n.setObservations(obs);
            this.text = text+n.genText();}
        else
            this.text = "Object already exists, discard";
    }
    
    public void pBary()
    {
    String[] ts = s.getName().split(" ");
    String bname = ts[0] + " " + ts[1];
    b = queryStarByName(bname);
    if(b == null){
    b = new Star();
    b.setDiscoverer(u);
    b.setParentId(s.getParentId());
    b.setObservations("Automatically Generated");
    b.setImgUrl(" ");
    b.setIsInNeb(s.getIsInNeb());
    b.setIsInClus(s.getIsInClus());
    b.setBaryLetter(null);
    b.setIsBary(true);
    b.setName(ts[0]+" "+ts[1]);
    this.text = text+b.genText();}
    else{s.setParentId(b.getId());
    b=null;}
    }
    
    /**
     *
     */
    public void pStar(){
    String stempUrl = this.imgUrl;String stempObs = this.obs;String stempName = this.name;String moded;
    s = queryStarByName(name);
    if(s==null){
        s = new Star();
        s.setDiscoverer(u);
        s.setIsInClus(false);
        s.setIsInNeb(false);
        s.setIsBary(false); 
        s.setName(name);
        s.setImgUrl(imgUrl);
        s.setObservations(obs);
        s.setParentId(0);
        if((boolean)gFlags.get("isInNeb")){
            s.setIsInNeb(true);
            if((boolean)gFlags.get("isMultiple")){
                try{
                if(split.length > 6){
                    if(split[7].matches("^(\\d+)") || split[7].charAt(0)=='S')
                        {s.setIsBary(true);}
                    else
                    {   moded = Character.toString(split[7].charAt(0));
                        s.setBaryLetter(moded);
                    }}}
            catch(ArrayIndexOutOfBoundsException e)
            {s.setIsBary(true);}}   
            Nebula neb =queryNebByName("RN "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]+"-"+split[5]);
            if(neb == null)
        {       stempUrl = this.imgUrl;stempObs = this.obs;stempName = this.name;
                this.imgUrl = " ";this.obs = "Automatically generated";this.name = "RN "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]+"-"+split[5];
                pNebula();
                this.imgUrl = stempUrl;this.obs = stempObs;this.name = stempName;}
            else{s.setParentId(neb.getId());}
        } 
        else if((boolean)gFlags.get("isInClus")){
            if((boolean)gFlags.get("isMultiple"))
            {if(split.length > 8){
                    try{if(split[9].matches("^(\\d+)") || split[9].charAt(0)=='S')
                        {s.setIsBary(true);}
                    else
                    {   moded = Character.toString(split[9].charAt(0));
                        s.setBaryLetter(moded);
                    }}
            catch(ArrayIndexOutOfBoundsException e)
            {s.setIsBary(true);}}
            }
            s.setIsInClus(true);
            Cluster clu=queryClusByName("RC "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]+"-"+split[5]);
            if(clu == null)
        {                     
                this.imgUrl = " ";this.obs = "Automatically generated";this.name = "RC "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]+"-"+split[5];
                pCluster();
                this.imgUrl = stempUrl;this.obs = stempObs;this.name = stempName;
        }else{s.setParentId(clu.getId());}}
        else {
           if((boolean)gFlags.get("isMultiple")){
               try{if(split.length >=9){
                    if(split[9].matches("^(\\d+)") || split[9].charAt(0)=='S')
                        {s.setIsBary(true);}
                    else
                    {   moded = Character.toString(split[9].charAt(0));
                        s.setBaryLetter(moded);
                    }}}
           catch(ArrayIndexOutOfBoundsException e)
           {
               s.setIsBary(true);}}
        Galaxy gal = queryGalByName("RG "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]);
        if(gal == null)
        {       stempUrl = this.imgUrl;stempObs = this.obs;
                this.imgUrl = " ";this.obs = "Automatically generated";
                pGal();
                this.imgUrl = stempUrl;this.obs = stempObs;}
        else{s.setParentId(gal.getId());}
        }
        if(s.getBaryLetter()!=null && (boolean)gFlags.get("isMultiple"))
        {
            pBary();
            s.setIsInClus(false);
        s.setIsInNeb(false);}
        this.text = this.text+s.genText();
        if(b!=null){s.setParentId(0);}}
    else
        this.text = "Object already exists, discard";}
    
    /**
     *
     */
    public void pMoon()
    {   Planet pParent;
        String ptempUrl;String ptempObs;String ptempName;
        m = queryBodyByName(name);
        if(m==null){
        m = new Planet();
        m.setDiscoverer(u);
        m.setAroundBary(false);
        m.setIsmoon(true);
        m.setName(this.name);
        m.setObservations(this.obs);
        m.setIsAsteroid((boolean)gFlags.get("isAsteroid"));
        m.setAroundBary(false);
        m.setImgUrl(this.imgUrl);
        m.setParentid(0);
        String pPlanet, pParentName;
        if(!(boolean)gFlags.get("isInNeb")){
                pPlanet = split[9];
                String[] pPlanetArray = pPlanet.split("[.]");
                pParentName = split[0]+" "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]+"-"+split[5]+"-"+split[6]+"-"+split[7]+"-"+split[8]+" "+pPlanetArray[0];}
            else
            {   pPlanet = split[7];
                String[] pPlanetArray = pPlanet.split("[.]");
                pParentName = split[0]+" "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]+"-"+split[5]+"-"+split[6]+" "+pPlanetArray[0];}
                pParent=queryBodyByName(pParentName);
            if(pParent == null){
                ptempUrl = this.imgUrl;ptempObs = this.obs;ptempName = this.name;               
                this.imgUrl = " ";this.obs = "Automatically generated";this.name = pParentName;
                gFlags.put("isMoon", false);
                pBody();
                gFlags.put("isMoon", true);
                this.imgUrl = ptempUrl;this.obs = ptempObs;this.name = ptempName;}
            else{m.setParentid(pParent.getId());}
            this.text = text+m.genText();
        }
        else
            this.text = "Object already exists, discard";
    }
    
    /**
     *
     */
    public void pBody()
    {
    String ptempUrl;String ptempObs;String ptempName;
     p = queryBodyByName(name);
    if(p==null){
        p = new Planet();
        p.setDiscoverer(u);
        p.setAroundBary(false);
        p.setIsmoon(false);
        p.setName(this.name);
        p.setObservations(this.obs);
        p.setIsAsteroid((boolean)gFlags.get("isAsteroid"));
        p.setImgUrl(this.imgUrl);
        p.setParentid(0);
        String moded, sName;
       if((boolean)gFlags.get("isInNeb")){
            if((boolean)gFlags.get("isMultiple")){
                if(split[7].matches("^(\\d+)") || split[7].charAt(0)=='S')
                {moded = "";
                p.setAroundBary(true);}
                else
                   moded = Character.toString(split[7].charAt(0));
                sName = split[0]+" "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]+"-"+split[5]+"-"+split[6]+" "+moded;}
                else{sName = split[0]+" "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]+"-"+split[5]+"-"+split[6];}
                Star st =queryStarByName(sName);
                if(st==null){      
                ptempUrl = this.imgUrl;ptempObs = this.obs;ptempName = this.name;               
                this.imgUrl = " ";this.obs = "Automatically generated";this.name = sName;
                pStar();
                this.imgUrl = ptempUrl;this.obs = ptempObs;this.name = ptempName;}
                else{p.setParentid(st.getId());}
        }
        else {
            if((boolean)gFlags.get("isMultiple")){
                if(split[9].matches("^(\\d+)") || split[9].charAt(0)=='S')
                {moded = "";
                p.setAroundBary(true);
                }
                else
                {moded = Character.toString(split[9].charAt(0));
                }              
                sName = split[0]+" "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]+"-"+split[5]+"-"+split[6]+"-"+split[7]+"-"+split[8]+" "+moded;}
            else{sName = split[0]+" "+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]+"-"+split[5]+"-"+split[6]+"-"+split[7]+"-"+split[8];}
            Star st=queryStarByName(sName);
                if(st==null){
                    ptempUrl = this.imgUrl;ptempObs = this.obs;ptempName = this.name;               
                    this.imgUrl = " ";this.obs = "Automatically generated";this.name = sName;
                    pStar();
                    this.imgUrl = ptempUrl;this.obs = ptempObs;this.name = ptempName;}
                else{p.setParentid(st.getId());}
              }
        this.text = text+p.genText();}  
    else
        this.text = "Object already exists, discard";
        }
}


