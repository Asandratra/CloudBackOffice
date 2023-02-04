package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import objet.Categorie;


public class CategorieController extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/plain"); 
        PrintWriter out = res.getWriter();
        Categorie c = null;
        try{
            c=new Categorie();
            req.setAttribute("categories",c.read());
            RequestDispatcher dispat = req.getRequestDispatcher("listeCategorie.jsp");
            dispat.forward(req,res);
        }
        catch(Exception ex){
            ex.printStackTrace();
            out.println(ex);
        } 
    }
}