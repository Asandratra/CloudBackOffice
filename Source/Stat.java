package objet;

import java.sql.*;
import java.util.*;
import java.time.*;
import java.lang.reflect.*;
import dao.Table;
import dao.Column;
import dao.ConnectDAO;

@Table(table="v_stat2")
public class Stat extends ObjetBdd{

    @Column(primary=true,insert=false,setter="setId",type=int.class,getter="getId")
    int id=-1;

    @Column(setter="setNom",type=String.class,getter="getNom")
    String nom;

    @Column(setter="setPrenom",type=String.class,getter="getPrenom")
    String prenom;

    @Column(setter="setUsername",type=String.class,getter="getUsername")
    String username;

    @Column(setter="setMdp",type=String.class,getter="getMdp")
    String mdp;

    @Column(setter="setMontant",type=double.class,getter="getMontant")
    double montant=-1;

    public Stat(){}

    public Stat(int i,String n,String p,String u,String m,double mo)throws Exception{
        try{
            setId(i);
            setNom(n);
            setPrenom(p);
            setUsername(u);
            setMdp(m);
            setMontant(mo);
        }
        catch(Exception e){
            throw e;
        }
    }
    public void setId(int i)throws Exception{
        if(i<0) throw new Exception("Negative value cannot be accepted");
        id=i;
    }
    public void setNom(String n)throws Exception{
        if(n==null || n.equals("")) throw new Exception("Value cannot be null or empty");
        nom=n;
    }
    public void setPrenom(String n)throws Exception{
        if(n==null || n.equals("")) throw new Exception("Value cannot be null or empty");
        prenom=n;
    }
    public void setUsername(String n)throws Exception{
        if(n==null || n.equals("")) throw new Exception("Value cannot be null or empty");
        username=n;
    }
    public void setMdp(String n)throws Exception{
        if(n==null || n.equals("")) throw new Exception("Value cannot be null or empty");
        mdp=n;
    }
    public void setMontant(double i)throws Exception{
        if(i<0) throw new Exception("Negative value cannot be accepted");
        montant=i;
    }
    public int getId(){
        return id;
    }
    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public String getUsername(){
        return username;
    }
    public String getMdp(){
        return mdp;
    }
    public double getMontant(){
        return montant;
    }
    public double totalGain()throws Exception{
        Connection co=null;
        double rep = 0;
        Object[] l = null;
        try{
            co = new ConnectDAO().getCon();
            l = new Stat().read(co);
            for(int i=0;i<l.length;i++){
                rep = rep+((Stat) l[i]).getMontant();
            }
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            if(co!=null) try { co.close(); } catch(Exception e){}
        }
        return rep;
    }
    public double getContribution()throws Exception{
        double total;
        double rep;
        try{
            total = this.totalGain();
            rep = this.montant/total;
        }
        catch(Exception ex){
            throw ex;
        }
        return rep;
    }
}