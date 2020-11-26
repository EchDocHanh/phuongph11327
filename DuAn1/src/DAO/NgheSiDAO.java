/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import mapper.NgheSiMapper;
import model.NgheSi;

/**
 *
 * @author Administrator
 */
public class NgheSiDAO implements GennericDAO<NgheSi>{

    @Override
    public List<NgheSi> findAll(String sql, Object... object) {
        return DAO.abstractDAO.query(sql,new NgheSiMapper(),object);   
    }

    @Override
    public NgheSi findOne(String sql, Object... object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(String sql, Object... object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String sql, Object... object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(String sql, Object... object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void main(String[] args) {
        System.out.println( new NgheSiDAO().findAll("select * from NGHESI").get(0).getTenNS());
       
    }
    
}
