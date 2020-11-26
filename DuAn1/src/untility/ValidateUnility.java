/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package untility;


import mapper.NgheSiMapper;
import mapper.TheLoaiMapper;

/**
 *
 * @author Administrator
 */
public class ValidateUnility {

    public static boolean ChekNgheSi(String name) {
        try {
            String[] arr = name.split(",");
            for (int i = 0; i < arr.length; i++) {
                String string = arr[i];
                DAO.abstractDAO.query("select * from NGHESI where ten = ?",new NgheSiMapper(),string).get(0);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static boolean ChekTheLoai(String name) {
        try {
            String[] arr = name.split(",");
            for (int i = 0; i < arr.length; i++) {
                String string = arr[i];
                DAO.abstractDAO.query("select * from THELOAI where tenTL = ?",new TheLoaiMapper(),string).get(0);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
