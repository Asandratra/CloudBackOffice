package objet;

import java.lang.reflect.*;
import dao.Table;
import dao.Column;

@Table(table="categorie")
public class Categorie extends ObjetBdd{

    @Column(primary=true,insert=false,setter="setId",type=int.class,getter="getId")
    int id=-1;

    @Column(setter="setNom",type=String.class,getter="getNom")
    String nom;

    public Categorie(){}

    public Categorie(int i,String n)throws Exception{
        try{
            setId(i);
            setNom(n);
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
    public int getId(){
        return id;
    }
    public String getNom(){
        return nom;
    }
}