/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gmall.model;
import java.util.*;
import java.lang.*;
/**
 *  A class to hold basic user information
 * @author noric
 */
public class User {
    private String name;
    private String password;
    private int tagb;
    
    public String getUserName() {
        return this.name;
    }
    
    public void setUserName(String userId) {
        this.name = userId;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String pass) {
        this.password = pass;
    }
    
    public int getTagb() {
        return this.tagb;
    }
    
    public void setTagb(int tag) {
        this.tagb = tag;
    }
}
