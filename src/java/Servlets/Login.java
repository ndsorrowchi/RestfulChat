/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Utils.DBSingleton;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import beans.userbean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.json.JSONObject;
/**
 *
 * @author chiming
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           
            String usr=request.getParameter("username");
            String pwd=request.getParameter("password");
            String alias="";
            String dbpwd="";
            RequestDispatcher rderr = getServletContext().getRequestDispatcher("/error.jsp");
            try{
                
                String sql="select password,nickname from users where uid=?";
//                InitialContext initialContext = new InitialContext();
//                Context context = (Context)initialContext.lookup("java:comp/env");
//                DataSource ds = (DataSource) context.lookup("mypool");
//                Connection con = ds.getConnection();
                Connection con = DBSingleton.getInstance().getConnection();
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, usr);
                ResultSet sqlres=ps.executeQuery();
                int count =0;
                while(sqlres.next())
                {
                    dbpwd=sqlres.getString(1);
                    alias=sqlres.getString(2);
                    count++;
                }
                if(count!=0 && pwd.equals(dbpwd))
                {
                    userbean msb=new userbean();
                    msb.setUid(usr);
                    msb.setUname(alias);
                    HttpSession session=request.getSession(true);
                    session.setMaxInactiveInterval(60*60);
                    session.setAttribute("usrbn", msb);
                    ps=con.prepareStatement("update users set status=1 where uid=?");
                    ps.setString(1, usr);
                    ps.executeUpdate();
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);
                }
                else if(count!=0 && !pwd.equals(dbpwd))
                {
                    request.setAttribute("errmsg", "Wrong Password. Please try again.");
                    rderr.forward(request, response);                    
                }
                else
                {
                    request.setAttribute("errmsg", "This Email is not registered. Please register first.");
                    rderr.forward(request, response);
                }
                
            }
            catch (SQLException sqle)
            {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, sqle);
                request.setAttribute("errmsg", sqle.getMessage());
                rderr.forward(request, response);                  
            } catch (Exception ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("errmsg", ex.getMessage());
                rderr.forward(request, response);                  
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
