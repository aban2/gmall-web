/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gmall.controller;

import gmall.model.UserHandler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.lang.*;

/**
 *This servlet server controls user Login and Signup Operation
 * @author noric
 */
@WebServlet(name = "UserManagementServlet", urlPatterns = {"/UserManagementServlet"})
public class UserManagementServlet extends HttpServlet {

     /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            String type = request.getParameter("type");
            UserHandler handl = new UserHandler();
            
            // processing user Login 
            if ("login".equals(type)) {
                String name = request.getParameter("username");
                String password = request.getParameter("password");
                // check if input match database records
                if (handl.checkUser(name, password)) {
                    // create session  <"user", User instance>  <"isLogin", boolean>
                    HttpSession session = request.getSession();
                    session.setAttribute("user", handl.getUserByName(name));
                    session.setAttribute("isLogin", true);
                    session.setMaxInactiveInterval(1800);
                    request.getRequestDispatcher("index.jsp").forward(request, response); 
                }
                else {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
             
            // processing user signup
            if ("signup".equals(type)) {
                String name = request.getParameter("Num");
                String password = request.getParameter("Key");
                if(handl.ifUserExist((name))) { // if user already exist
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                }
                else {
                    if (handl.userSignup(name, password)) { // sign up successfully
                        response.sendRedirect("login.jsp");
                    } else {
                        request.getRequestDispatcher("signup.jsp").forward(request, response);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.doPost(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
