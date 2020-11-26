/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.abstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.BaiHat;
import DAO.baihatDAO;
import dao.NgheSiDAO;
import dao.TheLoaiDAO;
import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.NgheSi;
import model.TheLoai;
/**
 *
 * @author Admin
 */

public class qlbaihat extends javax.swing.JInternalFrame {
    
    baihatDAO bhdao = new baihatDAO();
    NgheSiDAO nsDao = new NgheSiDAO();
    TheLoaiDAO tldao = new TheLoaiDAO();
    static String x = "";
    int index = 0;
    /**
     * Creates new form qlbaihat
     */
    public qlbaihat() {
        initComponents();
        tabs.setSelectedIndex(1);
        FillToTable();
        
        
    }
    void FillToTable(){
        DefaultTableModel model = (DefaultTableModel) tbbh.getModel();
        model.setRowCount(0);
        try {
             List<BaiHat> list = bhdao.findAll("select * from BAIHAT");
             for(BaiHat x : list){
                 String tenNS = "";
                 String tenTheLoai = "";
               List<NgheSi> list2  = nsDao.findAll("select * from BAIHAT_NGHESI inner join NGHESI on BAIHAT_NGHESI.MaNS = NGHESI.MaNS where MaBH = ?",x.getMaBH());
               List<TheLoai> list3  = tldao.findAll("select * from BAIHAT_THElOAI inner join THELOAI on BAIHAT_THElOAI.MaTL = THELOAI.MaTL where MaBH = ?",x.getMaBH());
                for (int i = 0; i < list2.size(); i++) {
                   tenNS = tenNS +","+ list2.get(i).getTenNS();  
                 }
                for (int i = 0; i < list3.size(); i++) {
                   tenTheLoai = tenTheLoai +","+ list3.get(i).getTenTL();  
                 }
                 Object[] row = {
                  x.getTenBH(),
                  x.getMaAB(),
                  tenNS,
                  x.getThoiLuong(),
                  tenTheLoai
                 };
                 model.addRow(row);
             } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void clean(){
        txttenbh.setBackground(Color.white);
        txtmaalbum.setBackground(Color.white);
        txtlyric.setBackground(Color.white);
        txtthoiluong.setBackground(Color.white);
        
    }
    void editTable(){
        clean();
        try {
            
            String tenbh = (String) tbbh.getValueAt(this.index, 0);
            BaiHat model = bhdao.findOne("select * from BaiHat where TenBH = ?",tenbh);
              String tenNS = "";
                 String tenTheLoai = "";
               List<NgheSi> list2  = nsDao.findAll("select * from BAIHAT_NGHESI inner join NGHESI on BAIHAT_NGHESI.MaNS = NGHESI.MaNS where MaBH = ?",model.getMaBH());
               List<TheLoai> list3  = tldao.findAll("select * from BAIHAT_THElOAI inner join THELOAI on BAIHAT_THElOAI.MaTL = THELOAI.MaTL where MaBH = ?",model.getMaBH());
                for (int i = 0; i < list2.size(); i++) {
                   tenNS = tenNS +","+ list2.get(i).getTenNS();  
                 }
                for (int i = 0; i < list3.size(); i++) {
                   tenTheLoai = tenTheLoai +","+ list3.get(i).getTenTL();  
                 }
            txttenbh.setText(model.getTenBH());
            txtmaalbum.setText(String.valueOf(model.getMaAB()));
            txtthoiluong.setText(String.valueOf(model.getThoiLuong()));
            txtlyric.setText(model.getLyric());
            jLabel3.setText(tenNS);
            jLabel9.setText(tenTheLoai);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void addBH(){
        BaiHat model = getModel();
        
        try {
            
            abstractDAO.update("insert into BAIHAT(tenBH,MaAlbum,thoiLuong,lyric)VALUES(?,?,?,?)"
                    ,txttenbh.getText(),Integer.parseInt(txtmaalbum.getText()),Integer.parseInt(txtthoiluong.getText()),txtlyric.getText());
            this.FillToTable();
            this.clean();
            JOptionPane.showMessageDialog(this,"Thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void updateBH(){
        BaiHat model = getModel();
        try {
            bhdao.update("insert into BAIHAT MaBH,tenBH,MaAlbuml,thoiLuong,lyric,ngayTao,nguoiTao values (?,?,?,?,?,?,?)"
                    ,txttenbh.getText(),txtmaalbum.getText(),txtthoiluong.getText(),txtlyric.getText(),txttenbh.getText()
                            );
            this.FillToTable();
            JOptionPane.showMessageDialog(this,"Thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void deleteBH(){
            String tenbh = txttenbh.getText();
            try {
                bhdao.delete(tenbh);
                this.FillToTable();
                this.clean();
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
    void setModel(BaiHat model){
        txttenbh.setText(model.getTenBH());
        txtmaalbum.setText(String.valueOf(model.getMaAB()));
        txtlyric.setText(model.getLyric());
        txtthoiluong.setText(String.valueOf(model.getThoiLuong()));
        
    }
    BaiHat getModel(){
        BaiHat bh = new BaiHat();
        bh.setTenBH(txttenbh.getText());
        bh.setMaAB(Integer.parseInt(txtmaalbum.getText()));
        bh.setLyric(txtlyric.getText());
        bh.setThoiLuong(Integer.parseInt(txtthoiluong.getText()));
        
        return bh;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbbh = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txttenbh = new javax.swing.JTextField();
        txtthoiluong = new javax.swing.JTextField();
        txtmaalbum = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtlyric = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setEnabled(false);
        setVisible(false);

        tabs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tbbh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "TÊN BÀI HÁT", "MÃ ALBUM", "CA SĨ", "THỜI LƯỢNG", "THỂ LOẠI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbbh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbbh);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );

        tabs.addTab("Danh Sách", new javax.swing.ImageIcon(getClass().getResource("/data/icons8_search_property_35px.png")), jPanel1); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên Bài Hát ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Mã Album");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Thời Lượng");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Ca sĩ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Lyric");

        txtlyric.setColumns(20);
        txtlyric.setRows(5);
        jScrollPane2.setViewportView(txtlyric);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Thể Loại");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/icons8_joyent_30px.png"))); // NOI18N
        jButton3.setText("Thêm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/icons8_update_30px.png"))); // NOI18N
        jButton4.setText("Sửa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/icons8_trash_can_30px.png"))); // NOI18N
        jButton5.setText("Xóa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("....");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/icons8_joyent_30px.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("....");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/icons8_joyent_30px.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addComponent(txtmaalbum))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(31, 31, 31)
                                .addComponent(jButton4)
                                .addGap(30, 30, 30)
                                .addComponent(jButton5))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttenbh, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtthoiluong, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttenbh, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(txtthoiluong))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmaalbum, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton3)
                                .addComponent(jButton4))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        tabs.addTab("Cập nhật", new javax.swing.ImageIcon(getClass().getResource("/data/icons8_edit_35px_1.png")), jPanel2); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý bài hát");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabs, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        addBH();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbbhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbhMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tbbh.rowAtPoint(evt.getPoint()); //lấy vị trí dòng được chọn
            if (this.index >= 0) {
                this.editTable();
                tabs.setSelectedIndex(1);
            }
        }
    }//GEN-LAST:event_tbbhMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        updateBH();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        deleteBH();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
        new FindItem<NgheSi>(nsDao.findAll("select * from NGHESI"),"Tên Nghệ Sĩ").setVisible(true);
            
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                }
                
              if(jLabel3.getText().equalsIgnoreCase("....")){
            jLabel3.setText(x);
        }else{
             jLabel3.setText(jLabel3.getText() + " , "+x);
        }
            }
           
        }.start();
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       new FindItem<TheLoai>(tldao.findAll("select * from THELOAI"),"Tên Thể Loại").setVisible(true);
            
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                }
                
              if(jLabel9.getText().equalsIgnoreCase("....")){
            jLabel9.setText(x);
        }else{
             jLabel9.setText(jLabel9.getText() + ","+x);
        }
            }
           
        }.start();
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbbh;
    private javax.swing.JTextArea txtlyric;
    private javax.swing.JTextField txtmaalbum;
    private javax.swing.JTextField txttenbh;
    private javax.swing.JTextField txtthoiluong;
    // End of variables declaration//GEN-END:variables
}
