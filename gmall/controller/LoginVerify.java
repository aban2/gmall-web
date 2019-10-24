/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gmall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class prepare a static method to verify if user is login
 * @author noric
 */

public class LoginVerify {
    
    public static boolean isLogin (HttpServletRequest request)
    {
        HttpSession sess = request.getSession();
        if(sess.getAttribute("isLogin") != null
                && sess.getAttribute("isLogin").equals(true) && sess.getAttribute("user")!=null) {
            return true;
        }
        return false;
    }
    
}
