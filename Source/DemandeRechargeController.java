package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import objet.DemandeRecharge;


public class DemandeRechargeController extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/plain"); 
        PrintWriter out = res.getWriter();
        DemandeRecharge d = null;
        try{
            d=new DemandeRecharge();
            d.setStatut(1);
            req.setAttribute("demandes",d.read());
            RequestDispatcher dispat = req.getRequestDispatcher("demandeRecharge.jsp");
            dispat.forward(req,res);
        }
        catch(Exception ex){
            ex.printStackTrace();
            out.println(ex);
        } 
    }
}