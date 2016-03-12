/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataAccess.MyUserService;
import Utils.ConvertModels;
import Models.UserRegisterModel;
import ViewModels.UserRegisterViewModel;
import beans.userbean;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chiming
 */
public class Register extends HttpServlet {

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
            
            String email=request.getParameter("username");
            String pswd=request.getParameter("password");
            String nickname=request.getParameter("nickname");
            String gender=request.getParameter("gender");
            String yob=request.getParameter("yob");
            
            UserRegisterViewModel urvm=new UserRegisterViewModel(email,pswd,nickname,yob,gender);
            UserRegisterModel urm=ConvertModels.view2db(urvm);
            
            boolean x=MyUserService.registerLogin(urm);
            
            userbean msb=new userbean();
                    
            msb.setUid(urm.getEmail());
            msb.setUname(urm.getNickname());
            HttpSession session=request.getSession(true);
            session.setMaxInactiveInterval(60*60);
            session.setAttribute("usrbn", msb);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            RequestDispatcher rderr = getServletContext().getRequestDispatcher("/error.jsp");
            request.setAttribute("errmsg", "Registration failed. Probably because your email has been registered before.");
            rderr.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            RequestDispatcher rderr = getServletContext().getRequestDispatcher("/error.jsp");
            request.setAttribute("errmsg", "Registration failed. Probably because your email has been registered before.");
            rderr.forward(request, response);
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
