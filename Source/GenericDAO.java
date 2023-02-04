package dao;

import java.text.*;
import java.util.*;
import java.sql.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import dao.Table;
import dao.Column;

public class GenericDAO {
    //Objectif insertion d'un objet a partir d'une fonction prenant comme parametre un objet.
    public String getTable(Object o){
        Class type = o.getClass();
        java.lang.annotation.Annotation a = type.getAnnotation(Table.class);
        Table t = (Table) a;
        if(t!=null && !(t.table().equals(""))){
            return t.table().toLowerCase();
        }
        return type.getSimpleName().toLowerCase();
    }
    public String[] getInsert(Object o){
        ArrayList l = new ArrayList();
        Column c = null;
        String name = null;
        for(Field attribut : o.getClass().getDeclaredFields()){
            try{
                attribut.setAccessible(true);
                name=attribut.getName();
                c=attribut.getAnnotation(Column.class);
                if(c!=null && c.insert()){
                    if(c.name().equals("")){
                        l.add(name.toLowerCase());
                    }
                    else{
                        l.add(c.name().toLowerCase());
                    }
                }
            }
            catch(Exception e){
                throw e;
            }
        }
        Object[] convert = new Object[l.size()];
        convert = l.toArray();
        String[] rep = new String[convert.length];
        int i;
        for(i=0;i<rep.length;i++){
            rep[i]=convert[i].toString();
        }
        return rep;
    }
    public String[] getColumns(Object o){
        ArrayList l = new ArrayList();
        Column c = null;
        String name = null;
        for(Field attribut : o.getClass().getDeclaredFields()){
            try{
                attribut.setAccessible(true);
                name=attribut.getName();
                c=attribut.getAnnotation(Column.class);
                if(c!=null && c.considerate()){
                    if(c.name().equals("")){
                        l.add(name.toLowerCase());
                    }
                    else{
                        l.add(c.name().toLowerCase());
                    }
                }
            }
            catch(Exception e){
                throw e;
            }
        }
        Object[] convert = new Object[l.size()];
        convert = l.toArray();
        String[] rep = new String[convert.length];
        int i;
        for(i=0;i<rep.length;i++){
            rep[i]=convert[i].toString();
        }
        return rep;
    }
    public String getPrimary(Object o){
        String rep = null;
        Column c = null;
        String name = null;
        for(Field attribut : o.getClass().getDeclaredFields()){
            try{
                attribut.setAccessible(true);
                name=attribut.getName();
                c=attribut.getAnnotation(Column.class);
                if(c!=null && c.primary() && c.considerate()){
                    if(c.name().equals("")){
                        rep = name.toLowerCase();
                    }
                    else{
                        rep = c.name().toLowerCase();
                    }
                    break;
                }
            }
            catch(Exception e){
                throw e;
            }
        }
        return rep;
    }
    public String getStringDate(Object o){
        java.util.Date d = (java.util.Date) o;
        SimpleDateFormat form= new SimpleDateFormat("yyyy-MM-dd");
        String rep= form.format(d);
        return rep;
    }

    public void insert(Object o,Connection co)throws Exception{
        int i;
        int y=0;
        PreparedStatement stmt = null;
        String table = this.getTable(o);
        String[] col = this.getInsert(o);
        String name = null;
        Object value = null;
        Column c = null;
        String insert="INSERT INTO "+table+"(";
        for(i=0;i<col.length;i++){
            insert=insert+col[i];
            if(i+1<col.length){
                insert=insert+",";
            }
        }
        insert = insert+")";
        insert = insert+" Values(";
        for(Field attribut : o.getClass().getDeclaredFields()){
            try{
                attribut.setAccessible(true);
                name=attribut.getName();
                value=attribut.get(o);
                c=attribut.getAnnotation(Column.class);
                if(c!=null && c.insert()&&value!=null){
                    if(value instanceof String){
                        if(y<=0){
                            insert = insert+"'"+value.toString()+"'";
                        }
                        else{
                            insert = insert+",'"+value.toString()+"'";
                        }
                    }
                    else if(value instanceof Number){
                        if(Double.parseDouble(value.toString())>=0){
                            if(y<=0){
                                insert = insert+value.toString();
                            }
                            else{
                                insert = insert+","+value.toString();
                            }
                        }
                    }
                    else if(value instanceof Boolean){
                        if(y<=0){
                            insert = insert+value.toString();
                        }
                        else{
                            insert = insert+","+value.toString();
                        }
                    }
                    else if(value instanceof java.util.Date){
                        if(y<=0){
                            insert = insert+"'"+this.getStringDate(value)+"'";
                        }
                        else{
                            insert = insert+",'"+this.getStringDate(value)+"'";
                        }
                    }
                    y++;
                }
            }
            catch(Exception e){
                throw e;
            }
        }
        insert=insert+")";
        System.out.println(insert);
        try{
            co.setAutoCommit(false);
            stmt = co.prepareStatement(insert);
            stmt.executeUpdate();
            co.commit();
        }
        catch(Exception ex){
            try{
                co.rollback();
            }
            catch(SQLException e){
                throw e;
            }
            throw ex;
        }
        finally{
            try{
                co.close();
                stmt.close();
            }
            catch(Exception est){
                throw est;
            }
        }
    }
    public void update(Object o,Connection co)throws Exception{
        int i;
        int y=0;
        PreparedStatement stmt = null;
        String table = this.getTable(o);
        String[] col = this.getColumns(o);
        String primary = this.getPrimary(o);
        String name = null;
        Object value = null;
        Object primval = null;
        Column c = null;
        String update="UPDATE "+table+" SET ";
        for(Field attribut : o.getClass().getDeclaredFields()){
            try{
                attribut.setAccessible(true);
                name=attribut.getName();
                value=attribut.get(o);
                c=attribut.getAnnotation(Column.class);
                if(c!=null && c.considerate()&&value!=null){
                    if(value instanceof String){
                        if(y<=0){
                            update = update+col[y]+"='"+value.toString()+"'";
                        }
                        else{
                            update = update+","+col[y]+"='"+value.toString()+"'";
                        }
                    }
                    else if(value instanceof Number){
                        if(Double.parseDouble(value.toString())>=0){
                            if(y<=0){
                                update = update+col[y]+"="+value.toString();
                            }
                            else{
                                update = update+","+col[y]+"="+value.toString();
                            }
                        }
                    }
                    else if(value instanceof Boolean){
                        if(y<=0){
                            update = update+col[y]+"="+value.toString();
                        }
                        else{
                            update = update+","+col[y]+"="+value.toString();
                        }
                    }
                    else if(value instanceof java.util.Date){
                        if(y<=0){
                            update = update+col[y]+"='"+this.getStringDate(value)+"'";
                        }
                        else{
                            update = update+","+col[y]+"='"+this.getStringDate(value)+"'";
                        }
                    }
                }
                if(c!=null&&c.primary()&&value!=null){
                    primval = attribut.get(o);
                }
                y++;
            }
            catch(Exception e){
                throw e;
            }
        }
        if(primval instanceof Number){
            update=update+" WHERE "+primary+" = "+Double.parseDouble(primval.toString());
        }
        else{
            update=update+" WHERE "+primary+" LIKE '"+primval.toString()+"'";
        }
        //System.out.println(update);
        try{
            co.setAutoCommit(false);
            stmt = co.prepareStatement(update);
            stmt.executeUpdate();
            co.commit();
        }
        catch(Exception ex){
            try{
                co.rollback();
            }
            catch(SQLException e){
                throw e;
            }
            throw ex;
        }
        finally{
            try{
                co.close();
                stmt.close();
            }
            catch(Exception est){
                throw est;
            }
        }
    }
    public void delete(Object o,Connection co)throws Exception{
        PreparedStatement stmt = null;
        String table = this.getTable(o);
        String primary = this.getPrimary(o);
        String name = null;
        Object value = null;
        Object primval = null;
        Column c = null;
        String delete="DELETE FROM "+table;
        for(Field attribut : o.getClass().getDeclaredFields()){
            try{
                attribut.setAccessible(true);
                name=attribut.getName();
                value=attribut.get(o);
                c=attribut.getAnnotation(Column.class);
                if(c!=null && c.primary()&&c.considerate()&&value!=null){
                    primval = attribut.get(o);
                }
            }
            catch(Exception e){
                throw e;
            }
        }
        if(primval instanceof Number){
            if(Double.parseDouble(primval.toString())>=0){
                delete=delete+" WHERE "+primary+" = "+Double.parseDouble(primval.toString());
            }
        }
        else{
            delete=delete+" WHERE "+primary+" LIKE '"+primval.toString()+"'";
        }
        //System.out.println(delete);
        try{
            co.setAutoCommit(false);
            stmt = co.prepareStatement(delete);
            stmt.executeUpdate();
            co.commit();
        }
        catch(Exception ex){
            try{
                co.rollback();
            }
            catch(SQLException e){
                throw e;
            }
            throw ex;
        }
        finally{
            try{
                co.close();
                stmt.close();
            }
            catch(Exception est){
                throw est;
            }
        }
    }
    public Object[] select(Object o,Connection co)throws Exception{
        String table = this.getTable(o);
        String select = "SELECT * FROM "+table;
        String where = " WHERE";
        String and = " AND";
        String name = null;
        Class cl = null;
        Object value = null;
        Column c = null;
        Object rep = null;
        Class ins=o.getClass();
        Method m;
        int i;
        int u;
        int y=0;
        PreparedStatement st = null;
        ResultSet rs = null;
        //ArrayList att = new ArrayList();
        //ArrayList type = new ArrayList();
        ArrayList conditions = new ArrayList();
        ArrayList result = new ArrayList();
        for(Field attribut : o.getClass().getDeclaredFields()){
            try{
                attribut.setAccessible(true);
                name=attribut.getName();
                cl=attribut.getType();
                value=attribut.get(o);
                c=attribut.getAnnotation(Column.class);
                if(c!=null && c.considerate()){
                    if(value!=null){
                        if(value instanceof Number){
                            if(Double.parseDouble(value.toString())>=0){
                                conditions.add(" "+name+" = "+value.toString());
                            }
                        }
                        else if(value instanceof Boolean){
                            conditions.add(" "+name+" = "+value.toString());
                        }
                        else if(value instanceof java.util.Date){
                            conditions.add(" "+name+" = '"+this.getStringDate(value)+"'::date");
                        }
                        else{
                            conditions.add(" "+name+" LIKE '"+value.toString()+"'");
                        }
                    }
                }
            }
            catch(Exception e){
                throw e;
            }
        }
        if(conditions.size()>0){
            select=select+where;
            for(i=0;i<conditions.size();i++){
                select=select + conditions.get(i).toString();
                if(i+1<conditions.size()){
                    select=select+and;
                }
            }
        }
        //System.out.println(select);
        try{
            st = co.prepareStatement(select);
            rs = st.executeQuery();
            while(rs.next()){
                rep = o.getClass().newInstance();
                u=1;
                for(Field attribut : o.getClass().getDeclaredFields()){
                    attribut.setAccessible(true);
                    c = attribut.getAnnotation(Column.class);
                    if(c!=null){
                        m = ins.getMethod(c.setter(),c.type());
                        if(int.class.isAssignableFrom(c.type())){
                            m.invoke(rep,rs.getInt(u));
                        }
                        else if(double.class.isAssignableFrom(c.type())){
                            m.invoke(rep,rs.getDouble(u));
                        }
                        else if(boolean.class.isAssignableFrom(c.type())){
                            m.invoke(rep,rs.getBoolean(u));
                        }
                        else if(java.util.Date.class.isAssignableFrom(c.type())){
                            m.invoke(rep,(java.util.Date) rs.getDate(u));
                        }
                        else{
                            m.invoke(rep,rs.getString(u));
                        }
                        u++;
                    }
                }
                result.add(rep);
            }
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            try{
                co.close();
                st.close();
            }
            catch(Exception est){
                throw est;
            }
        }
        Object[] retour=new Object[result.size()];
        retour = result.toArray();
        return retour;
    }
    public Object[] selectView(Object o,String table,Connection co)throws Exception{
        String select = "SELECT * FROM "+table;
        String where = " WHERE";
        String and = " AND";
        String name = null;
        Class cl = null;
        Object value = null;
        Column c = null;
        Object rep = null;
        Class ins=o.getClass();
        Method m;
        int i;
        int u;
        int y=0;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList att = new ArrayList();
        ArrayList type = new ArrayList();
        ArrayList conditions = new ArrayList();
        ArrayList result = new ArrayList();
        for(Field attribut : o.getClass().getDeclaredFields()){
            try{
                attribut.setAccessible(true);
                name=attribut.getName();
                cl=attribut.getType();
                value=attribut.get(o);
                c=attribut.getAnnotation(Column.class);
                if(c!=null && c.considerate()){
                    att.add(name);
                    type.add(cl);
                    if(value!=null){
                        if(value instanceof Number){
                            if(Double.parseDouble(value.toString())>=0){
                                conditions.add(" "+name+" = "+value.toString());
                            }
                        }
                        else if(value instanceof java.util.Date){
                            conditions.add(" "+name+" = '"+this.getStringDate(value)+"'::date");
                        }
                        else{
                            conditions.add(" "+name+" LIKE '"+value.toString()+"'");
                        }
                    }
                }
            }
            catch(Exception e){
                throw e;
            }
        }
        if(conditions.size()>0){
            select=select+where;
            for(i=0;i<conditions.size();i++){
                select=select + conditions.get(i).toString();
                if(i+1<conditions.size()){
                    select=select+and;
                }
            }
        }
        //System.out.println(select);
        try{
            st = co.prepareStatement(select);
            rs = st.executeQuery();
            while(rs.next()){
                rep = o.getClass().newInstance();
                u=1;
                for(Field attribut : o.getClass().getDeclaredFields()){
                    attribut.setAccessible(true);
                    c = attribut.getAnnotation(Column.class);
                    if(c!=null){
                        m = ins.getMethod(c.setter(),c.type());
                        if(int.class.isAssignableFrom(c.type())){
                            m.invoke(rep,rs.getInt(u));
                        }
                        else if(double.class.isAssignableFrom(c.type())){
                            m.invoke(rep,rs.getDouble(u));
                        }
                        else if(boolean.class.isAssignableFrom(c.type())){
                            m.invoke(rep,rs.getBoolean(u));
                        }
                        else if(java.util.Date.class.isAssignableFrom(c.type())){
                            m.invoke(rep,(java.util.Date) rs.getDate(u));
                        }
                        else if(Time.class.isAssignableFrom(c.type())){
                            m.invoke(rep, rs.getTime(u));
                        }
                        else{
                            m.invoke(rep,rs.getString(u));
                        }
                        u++;
                    }
                }
                result.add(rep);
            }
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            try{
                co.close();
                st.close();
            }
            catch(Exception est){
                throw est;
            }
        }
        Object[] retour=new Object[result.size()];
        retour = result.toArray();
        return retour;
    }
}
