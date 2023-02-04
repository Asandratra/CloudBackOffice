package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import objet.Admin;
import objet.Commission;
import objet.Stat;


public class StatController extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/plain"); 
        PrintWriter out = res.getWriter();
        Commission c = null;
        Stat s = null;
        try{
            c=new Commission();
            s=new Stat();
            req.setAttribute("commission",c.getLastCommission());
            req.setAttribute("stats",s.read());
            req.setAttribute("total",s.totalGain());
            RequestDispatcher dispat = req.getRequestDispatcher("stat.jsp");
            dispat.forward(req,res);
        }
        catch(Exception ex){
            ex.printStackTrace();
            out.println(ex);
        } 
    }
}