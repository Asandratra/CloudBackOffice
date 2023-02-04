package objet;

import java.sql.*;
import java.util.*;
import java.time.*;
import java.lang.reflect.*;
import dao.Table;
import dao.Column;
import dao.ConnectDAO;

@Table(table="compte")
public class Compte extends ObjetBdd{


    @Column(setter="setIdUtilisateur",type=int.class,getter="getIdUtilisateur")
    int idUtilisateur=-1;

    @Column(setter="setDaty",type=java.util.Date.class,getter="getDaty")
    java.util.Date daty;

    @Column(setter="setMontant",type=double.class,getter="getMontant")
    double montant=-1;

    public Compte(){}

    public Compte(int i,java.util.Date d,double mo)throws Exception{
        try{
            setIdUtilisateur(i);
            setDaty(d);
            setMontant(mo);
        }
        catch(Exception e){
            throw e;
        }
    }
    public void setIdUtilisateur(int i)throws Exception{
        if(i<0) throw new Exception("Negative value cannot be accepted");
        idUtilisateur=i;
    }
    public void setDaty(java.util.Date i)throws Exception{
        if(i==null) throw new Exception("Value can't be null");
        daty=i;
    }
    public void setMontant(double i)throws Exception{
        if(i<0) throw new Exception("Negative value cannot be accepted");
        montant=i;
    }
    public int getIdUtilisateur(){
        return idUtilisateur;
    }
    public java.util.Date getDaty(){
        return daty;
    }
    public double getMontant(){
        return montant;
    }
}