/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gmall.model;

import java.sql.*;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 *  Get connection to G-mall database
 * @author noric
 */
public class DBConnection {
    private Connection conn = null;
    private String url = "jdbc:mysql://localhost:3306/gmall";
    private String user = "root";
    private String password = "970629";
    
    public Connection getConnection() {
        try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return conn;
    }
}
