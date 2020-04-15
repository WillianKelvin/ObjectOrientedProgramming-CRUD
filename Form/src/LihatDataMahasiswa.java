
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class LihatDataMahasiswa extends JFrame {
    String[][] data = new String[500][4];
    String[] kolom = {"Nim","Nama","Alamat"};
    JTable tabel;
    JScrollPane scrollPane;
    JButton btnBack;
    String DBurl = "jdbc:mysql;//localhost/mahsiswa";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;
    ResultSet resultSet;
    
    public LihatDataMahasiswa(){
        setTitle("Data Mahasiswa");
        
        btnBack = new JButton("Kembali");
        tabel = new JTable(data,kolom);
        scrollPane = new JScrollPane(tabel);
      
        KoneksiDB koneksi = new KoneksiDB(); //memanggil kelas KoneksiDB
        try{
            
            statement = koneksi.getKoneksi().createStatement();
            String sql = "SELECT *FROM data_mhs"; // data_mhs sesuai di dtbase
            resultSet = statement.executeQuery(sql);
            
            int p = 0;
            while (resultSet.next()){
                data[p][0] = resultSet.getString("nim"); // disesuaikan nama tabel database yang dibuat
                data[p][1] = resultSet.getString("nama");
               // data[p][2] = resultSet.getString("jk");
                data[p][2] = resultSet.getString("alamat");
                p++;                           
            }
            statement.close();
           
            } catch (SQLException sqle){
                JOptionPane.showMessageDialog(rootPane, "Data gagal ditampilkan!" + sqle);
            } catch (ClassNotFoundException classe) {
                JOptionPane.showMessageDialog(rootPane, "Class Tidak ditemukan!");
        }
   
        setLayout(new FlowLayout());
        add(scrollPane);
        add(btnBack);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new formmhs();
            }
        });
        }
    }
       
  
