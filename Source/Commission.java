package objet;

import java.sql.*;
import java.util.*;
import java.time.*;
import java.lang.reflect.*;
import dao.Table;
import dao.Column;
import dao.ConnectDAO;

@Table(table="commission")
public class Commission extends ObjetBdd{

    @Column(primary=true,insert=false,setter="setId",type=int.class,getter="getId")
    int id=-1;

    @Column(setter="setPourcentage",type=double.class,getter="getPourcentage")
    double pourcentage=-1;

    public Commission(){}

    public Commission(int i,double s)throws Exception{
        try{
            setId(i);
            setPourcentage(s);
        }
        catch(Exception e){
            throw e;
        }
    }
    public void setId(int i)throws Exception{
        if(i<0) throw new Exception("Negative value cannot be accepted");
        id=i;
    }
    public void setPourcentage(double i)throws Exception{
        if(i<0) throw new Exception("Negative value cannot be accepted");
        pourcentage=i;
    }
    public double getPourcentage(){
        return pourcentage;
    }
    public Commission getLastCommission()throws Exception{
        Connection co = null;
        PreparedStatement st=null;
        ResultSet rs=null;
        String sql = "SELECT max(id) as id FROM commission";
        Commission rep = null;
        try{
            co = new ConnectDAO().getCon();
            st=co.prepareStatement(sql);
            rs=st.executeQuery();
            rep=new Commission();
            while(rs.next()){
                rep.setId(rs.getInt("id"));
            }
            rep = (Commission) rep.read(co)[0];
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
        return rep;
    }
}