/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gmall.model;

import java.sql.*;
import java.lang.*;
import java.util.*;

/**
 * This class Offers user related Operations
 * @author noric
 */
public class UserHandler {
    
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    
    /**
     * Get User Class by UserId
     * @param userName   target UserId
     * @return 
     */
    public User getUserByName (String userName) {
        User user = new User();
        try {
           DBConnection dbcon = new DBConnection();
           con = dbcon.getConnection();
           pstmt = con.prepareStatement("SELECT * FROM Userinfo WHERE userId=?");
           pstmt.setString(1, userName);
           rs = pstmt.executeQuery();
           if(rs.next()) {
               user.setUserName(rs.getString(1));
               user.setPassword(rs.getString(2));
               user.setTagb(rs.getInt(3));
           }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return user;
    }
    
    /**
     * A method to check if UserName and password match
     * @param name  name of target user
     * @param password  password of target user
     * @return 
     */
    public boolean checkUser (String name, String password) {
        boolean success = false;
        try {
            DBConnection dbcon = new DBConnection();
            con = dbcon.getConnection();
            pstmt = con.prepareStatement("SELECT password FROM Userinfo WHERE userId=?");
            pstmt.setString(1,name);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String correctPass = rs.getString(1);
                if(password.equals(correctPass)) {
                    success = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return success;
    }
    
    /**
     * A method to update G-mall database inorder to get user signuped
     * @param name  user set name 
     * @param password user set password
     * @return 
     */
    public boolean userSignup (String name, String password) {
        boolean success = false;
        try {
            DBConnection dbcon = new DBConnection();
            con = dbcon.getConnection();
            pstmt = con.prepareStatement("INSERT INTO Userinfo (userId, password, tagb) VALUES (?,?,?)");
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setInt(3, 0);
            int num = pstmt.executeUpdate();
            if(num == 1) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return success;
    }
    
    /**
     * Check if this user name is occupied
     * @param name user name to be checked
     * @return 
     */
    public boolean ifUserExist (String name) {
        boolean exist = false;
        DBConnection dbcon = new DBConnection();
        try {
            con = dbcon.getConnection();
            pstmt = con.prepareStatement("SELECT COUNT(userId) FROM Userinfo WHERE userId=?");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                int number = rs.getInt(1);
                if(number == 1) exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return exist;
    }
    
    /**
     * Close database connection related resourses
     */
    public void close () {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (pstmt != null) {
                pstmt.close();
                pstmt = null;
            }
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
