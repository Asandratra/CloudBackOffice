package dao;

import java.util.*;
import java.sql.*;

public class ConnectDAO {
    private String driver = "org.postgresql.Driver";
    private Connection co;
    private String connect = "jdbc:postgresql://containers-us-west-128.railway.app:6888/railway";
    private String user = "postgres";
    private String password = "wkXSn5mOaZrOcYXltRuN";

    public void setDriver(String c) {
        this.driver = c;
    }

    public void setConnect(String c) {
        this.connect = c;
    }

    public void setUser(String c) {
        this.user = c;
    }

    public void setPassword(String c) {
        this.password = c;
    }

    public ConnectDAO() {
    }

    public ConnectDAO(String d, String c, String u, String p) {
        setDriver(d);
        setConnect(c);
        setUser(u);
        setPassword(p);
    }

    public String getDriver() {
        return this.driver;
    }

    public Connection getCon() throws Exception {
        try {
            Class.forName(this.getDriver());
            co = DriverManager.getConnection(connect, user, password);
        } catch (Exception e) {
            throw e;
        }
        return this.co;
    }

    public String getConName() {
        return this.connect;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }
}