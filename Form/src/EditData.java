
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class EditData extends JFrame {
    JLabel lnim, lNama, lAlamat, lJudul;
    JTextField tfnim, tfNama , tfAlamat;
    JButton btnUpdate, btnKembali;
    Statement statement;
    ResultSet resultSet;
    
    public EditData(){
        lJudul = new JLabel ("Masukkan NIM yang akan DiUpdate");
        lnim = new JLabel("NIM");
        lNama = new JLabel("Nama");
        lAlamat = new JLabel("Alamat");
        
        tfnim = new JTextField();
        tfNama = new JTextField();
        tfAlamat = new JTextField();
        
        btnUpdate = new JButton("Update");
        btnKembali = new JButton("Kembali");
        
        setTitle("Update Data Pegawai");
        setSize(300,370);
        lJudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        setLayout(null);
        add(lJudul);
        add(lnim);
        add(lNama);
        add(lAlamat);
        add(tfnim);
        add(tfNama);
        add(tfAlamat);
        add(btnUpdate);
        add(btnKembali);
        
        lJudul.setBounds(0, 10, 300, 25);
        lnim.setBounds(50, 50, 100, 25);
        tfnim.setBounds(100, 50, 100, 25);
        lNama.setBounds(50, 90, 100, 25);
        tfNama.setBounds(100, 90, 100, 25);
        lAlamat.setBounds(50, 210, 100, 25);
        tfAlamat.setBounds(100, 210, 100, 25);
        btnKembali.setBounds(30, 290, 100, 25);
        btnUpdate.setBounds(140, 290, 100, 25);
        
        btnKembali.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new formmhs();
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                btnUpdateactionListener();
            }

            private void btnUpdateactionListener() {
                KoneksiDB koneksi = new KoneksiDB();
                try {
                    statement = koneksi.getKoneksi().createStatement();
                    statement.executeUpdate("UPDATE data_mhs SET nama='" + tfNama.getText() +"',"+ "alamat='"+
                            tfAlamat.getText() + "' WHERE nim='"+tfnim.getText()+"'"); // update berdasarkan nim
                    JOptionPane.showMessageDialog(null, "Data berhasil di Update!","Hasil",JOptionPane.INFORMATION_MESSAGE);
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal diupdate!","Hasil",JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex){
                    JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!","Hasil",JOptionPane.ERROR_MESSAGE);
                }
 
            }
        });
    }
}
