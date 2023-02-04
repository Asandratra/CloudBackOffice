package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import objet.Categorie;


public class AjoutCategorieController extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/plain"); 
        PrintWriter out = res.getWriter();
        Categorie c = null;
        try{
            c=new Categorie();
            c.setNom(req.getParameter("nom"));
            c.create();
            req.setAttribute("categories",new Categorie().read());
            RequestDispatcher dispat = req.getRequestDispatcher("listeCategorie.jsp");
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
            RequestDispatcher dispat = req.getRequestDispatcher("formCategorie.jsp");
            dispat.forward(req,res);
        }
        catch(Exception ex){
            ex.printStackTrace();
            out.println(ex);
        } 
    }
}