package objet;

import java.sql.*;
import java.util.*;
import java.time.*;
import java.lang.reflect.*;
import dao.Table;
import dao.Column;
import dao.ConnectDAO;

@Table(table="v_demanderecharge")
public class DemandeRecharge extends ObjetBdd{


    @Column(name="id",setter="setIdUtilisateur",type=int.class,getter="getIdUtilisateur")
    int idUtilisateur=-1;

    @Column(setter="setNom",type=String.class,getter="getNom")
    String nom;

    @Column(setter="setPrenom",type=String.class,getter="getPrenom")
    String prenom;

    @Column(setter="setUsername",type=String.class,getter="getUsername")
    String username;

    @Column(setter="setMdp",type=String.class,getter="getMdp")
    String mdp;

    @Column(primary=true,insert=false,setter="setIdDemandeRecharge",type=int.class,getter="getIdDemandeRecharge")
    int idDemandeRecharge=-1;

    @Column(setter="setDaty",type=java.util.Date.class,getter="getDaty")
    java.util.Date daty;

    @Column(setter="setMontant",type=double.class,getter="getMontant")
    double montant=-1;

    @Column(setter="setStatut",type=int.class,getter="getStatut")
    int statut=-1;

    public DemandeRecharge(){}

    public DemandeRecharge(int i,String n,String p,String u,String m,int idr,java.util.Date d,double mo,int s)throws Exception{
        try{
            setIdUtilisateur(i);
            setNom(n);
            setPrenom(p);
            setUsername(u);
            setMdp(m);
            setIdDemandeRecharge(idr);
            setDaty(d);
            setMontant(mo);
            setStatut(s);
        }
        catch(Exception e){
            throw e;
        }
    }
    public void setIdUtilisateur(int i)throws Exception{
        if(i<0) throw new Exception("Negative value cannot be accepted");
        idUtilisateur=i;
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
    public void setIdDemandeRecharge(int i)throws Exception{
        if(i<0) throw new Exception("Negative value cannot be accepted");
        idDemandeRecharge=i;
    }
    public void setDaty(java.util.Date i)throws Exception{
        if(i==null) throw new Exception("Value can't be null");
        daty=i;
    }
    public void setMontant(double i)throws Exception{
        if(i<0) throw new Exception("Negative value cannot be accepted");
        montant=i;
    }
    public void setStatut(int i)throws Exception{
        if(i<0) throw new Exception("Negative value cannot be accepted");
        statut=i;
    }
    public int getIdUtilisateur(){
        return idUtilisateur;
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
    public int getIdDemandeRecharge(){
        return idDemandeRecharge;
    }
    public java.util.Date getDaty(){
        return daty;
    }
    public double getMontant(){
        return montant;
    }
    public int getStatut(){
        return statut;
    }
    public void confirm()throws Exception{
        Connection co = null;
        PreparedStatement st=null;
        ResultSet rs=null;
        String sql = "UPDATE demanderecharge SET statut = 0 WHERE id = ?";
        Compte c=null;
        try{
            co = new ConnectDAO().getCon();
            st=co.prepareStatement(sql);
            st.setInt(1,idDemandeRecharge);
            st.executeUpdate();
            c = new Compte();
            c.setIdUtilisateur(idUtilisateur);
            c.setDaty(new java.sql.Date(System.currentTimeMillis()));
            c.setMontant(montant);
            c.create(co);
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            try{
                if(co!=null){
                    co.close();
                }
                if(st!=null){
                    st.close();
                }
                if(rs!=null){
                    rs.close();
                }
            }
            catch(Exception e){
                throw e;
            }
        }
    }
}