package objet;

import java.sql.*;
import dao.GenericDAO;
import dao.ConnectDAO;

public class ObjetBdd extends GenericDAO {
    public ObjetBdd(){}
    public void create(Connection co) throws Exception {
        super.insert(this, co);
    }
    public void update(Connection co) throws Exception {
        super.update(this, co);
    }
    public void delete(Connection co) throws Exception {
        super.delete(this, co);
    }
    public ObjetBdd[] read(Connection co) throws Exception {
        Object[] ao = super.select(this,co);
        ObjetBdd[] rep = new ObjetBdd[ao.length];
        int  i ;      
        for(i=0;i<ao.length;i++){
            rep[i] = (ObjetBdd) ao[i];
        }
        return rep;
    }
    public void create() throws Exception {
        Connection co = new ConnectDAO().getCon();
        super.insert(this, co);
    }
    public void update() throws Exception {
        Connection co = new ConnectDAO().getCon();
        super.update(this, co);
    }
    public void delete() throws Exception {
        Connection co = new ConnectDAO().getCon();
        super.delete(this, co);
    }
    public ObjetBdd[] read() throws Exception {
        Connection co = new ConnectDAO().getCon();
        Object[] ao = super.select(this,co);
        ObjetBdd[] rep = new ObjetBdd[ao.length];
        int  i ;      
        for(i=0;i<ao.length;i++){
            rep[i] = (ObjetBdd) ao[i];
        }
        return rep;
    }
}
