/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.TaiKhoan;

/**
 *
 * @author Admin
 */
public class userDAO {
    
       public TaiKhoan findOne (String sql , Object ... object){
            TaiKhoan tk = new TaiKhoan();
            try {
                
                PreparedStatement ps = abstractDAO.getConnection().prepareCall(sql);
               
               
                
                for (int i = 0 ; i < object.length;i++ ) {
                   ps.setObject(i+1, object[i]);
                }
                 ResultSet rs = ps.executeQuery();
                rs.next();
                tk.setTaiKhoan(rs.getString("taiKhoan"));
                tk.setMatKhau(rs.getString("matKhau"));
             
                tk.setVaiTro(rs.getBoolean("vaitro"));
                
            } catch (Exception e) {
                 System.out.println("sai3");
            }
            return tk;
            
        }
      
       public void update(String sql , Object ... object){
           try {
              PreparedStatement ps = abstractDAO.getConnection().prepareCall(sql);
 
                for (int i = 0 ; i < object.length;i++ ) {
                   ps.setObject(i+1, object[i]);
                }
                ps.executeUpdate();
                
           } catch (Exception e) {
           }
           
       }
}
