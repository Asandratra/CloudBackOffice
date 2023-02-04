package objet;

import java.sql.*;
import java.lang.reflect.*;
import dao.Table;
import dao.Column;
import dao.ConnectDAO;

@Table(table="admin")
public class Admin extends ObjetBdd{

    @Column(primary=true,insert=false,setter="setId",type=int.class,getter="getId")
    int id=-1;

    @Column(setter="setUsername",type=String.class,getter="getUsername")
    String username;

    @Column(setter="setMdp",type=String.class,getter="getMdp")
    String mdp;

    public Admin(){}

    public Admin(int i,String u,String m)throws Exception{
        try{
            setId(i);
            setUsername(u);
            setMdp(m);
        }
        catch(Exception e){
            throw e;
        }
    }
    public void setId(int i)throws Exception{
        if(i<0) throw new Exception("Negative value cannot be accepted");
        id=i;
    }
    public void setUsername(String n)throws Exception{
        if(n==null || n.equals("")) throw new Exception("Value cannot be null or empty");
        username=n;
    }
    public void setMdp(String n)throws Exception{
        if(n==null || n.equals("")) throw new Exception("Value cannot be null or empty");
        mdp=n;
    }
    public int getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getMdp(){
        return mdp;
    }
    public Admin login(String u,String m)throws Exception{
        Connection co = null;
        Object[] l = null;
        Admin rep = null;
        try{
            setUsername(u);
            setMdp(m);
            co = new ConnectDAO().getCon();
            l = this.read(co);
            rep = (Admin) l[0];
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            if(co!=null) try{ co.close(); }catch(Exception e){}
        }
        return rep;
    }
}