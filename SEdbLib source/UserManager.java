/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SEdbLib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Mahzel
 */

public class UserManager {
    private boolean logged_in;
    private int uid;
    private String nick;
    
    /**
     *
     * @param id
     */
    public void setUid(int id)
    {uid = id;}
    
    /**
     *
     * @return
     */
    public int getUid()
    {return uid;}
    
    /**
     *
     * @param log
     */
    public void set_log(boolean log)
    {logged_in = log;}
    
    /**
     *
     * @return
     */
    public boolean getLog()
    {return logged_in;}
    
    /**
     *
     * @param Nick
     */
    public void setNick(String Nick)
    {nick = Nick;}
    
    /**
     *
     * @return
     */
    public String getNick()
    {return nick;}
    
    /**
     *
     * @param log
     * @param id
     * @param Nick
     */
    public void UserManager(boolean log, int id, String Nick)
    {   logged_in = log;
        uid = id;
        nick = Nick;
    }
    
     /**
     *
     * @param input
     * @return
     */
     /**
     *
     * @param nick
     * @return
     */
    public static Users QueryByName(String nick) {
        EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        Query query = em.createNamedQuery("Users.findByNick").setParameter("nick", nick).setMaxResults(1);
        List<Users> result = query.getResultList();
        try{result.get(0);}
        catch(java.lang.ArrayIndexOutOfBoundsException exc)
        {return null;}
        Users u = result.get(0);
        em.close();
        return u;
    }
    
    /**
     *
     * @param Nick
     * @param Pass
     */
    public void UserLogin(String Nick, String Pass) {       
           Users UserRes;
           String passhash;
           passhash = SELib.getMD5(Pass);
           UserRes = UserManager.QueryByName(Nick);
           if(UserRes == null)
           {System.out.println("User doesn't exist! Register?");
           set_log(false);
           }
           else if(passhash.equals(UserRes.getPass()))
           {
            setUid(UserRes.getId());
            setNick(Nick);
            System.out.println(getNick() + " logged in!");
            set_log(true);
           }
           else
           {setNick(Nick);
           System.out.println(getNick() + " notlogged in!");
           set_log(false);}
    }
    
    /**
     *
     * @param Nick
     * @param Pass
     */
    public void UserRegister(String Nick, String Pass) {
        Users user = UserManager.QueryByName(Nick);
        if(user!=null || Pass==null)
        {System.out.println("User already registered or password empty!");
         return;}
        user = new Users();
        user.setPass(SELib.getMD5(Pass));
        user.setUser(Nick);
        EntityManager em = Persistence.createEntityManagerFactory("SEdbLibPU").createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }
}

