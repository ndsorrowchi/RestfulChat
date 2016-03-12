/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.sql.DataSource;
import org.json.*;
/**
 *
 * @author chiming
 */
public class Check extends HttpServlet {

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
            //Logger.getLogger(Check.class.getName()).info("came in here");
            StringBuilder sb=new StringBuilder();
            BufferedReader br=request.getReader();
            String str;
            while( (str = br.readLine()) != null ){
                sb.append(str);
            }    
            JSONObject obj = new JSONObject(sb.toString());
            String sql="select count(*) from users where uid=?";
            try{
                InitialContext initialContext = new InitialContext();
                Context context = (Context)initialContext.lookup("java:comp/env");
                DataSource ds = (DataSource) context.lookup("mypool");
                Connection con = ds.getConnection();
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, obj.getString("useremail"));
                ResultSet rs=ps.executeQuery();
                int count=-1;
                if(rs.next()){
                count=rs.getInt(1);}
                JSONObject ret=new JSONObject();
                ret.put("count", count);
                out.println(ret.toString());
            } catch (SQLException sqle) {
                String responseText="{\"error\":\""+sqle.getMessage()+"\"}";
                Logger.getLogger(Check.class.getName()).log(Level.SEVERE, null, sqle);
                out.println(responseText);
            } catch (NamingException ex) {
                String responseText="{\"error\":\""+ex.getMessage()+"\"}";
                out.println(responseText);
                Logger.getLogger(Check.class.getName()).log(Level.SEVERE, null, ex);
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
