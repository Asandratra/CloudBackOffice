package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import objet.Admin;
import objet.Commission;
import objet.Stat;


public class IndexController extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/plain"); 
        PrintWriter out = res.getWriter();
        Admin a = null;
        Commission c = null;
        Stat s = null;
        String page = null;
        try{
            a = new Admin().login(req.getParameter("username"),req.getParameter("mdp"));
            if(a!=null){
                c=new Commission();
                s=new Stat();
                req.setAttribute("commission",c.getLastCommission());
                req.setAttribute("stats",s.read());
                req.setAttribute("total",s.totalGain());
                page = "stat.jsp";
            }
            else{
                req.setAttribute("message","Erreur d'authentification, veuillez reessayer");
                page = "";
            }
            RequestDispatcher dispat = req.getRequestDispatcher(page);
            dispat.forward(req,res);
        }
        catch(Exception ex){
            ex.printStackTrace();
            out.println(ex);
        } 
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/plain"); 
        PrintWriter out = res.getWriter();
        try{
            RequestDispatcher dispat = req.getRequestDispatcher("login.jsp");
            dispat.forward(req,res);
        }
        catch(Exception ex){
            ex.printStackTrace();
            out.println(ex);
        } 
    }
}