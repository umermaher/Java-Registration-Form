package guiassignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectWithDB {
    InfoDialog info;
    Connection con;
    public ConnectWithDB(InfoDialog info){
        this.info=info;
    }
    public void createConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Diver loaded");
            con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root","root");
            System.out.println("Database Connected");
            insertDataIntoSQL();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception: "+ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception: "+ex.getMessage());
        }
    }
    public void insertDataIntoSQL(){
        try {
            PreparedStatement ps=con.prepareCall("INSERT INTO CANDIDATES VALUES(?,?,?,?,?,?)");
            ps.setString(1, info.getName());
            ps.setString(2,info.getEmail());
            ps.setString(3, info.getGender());
            ps.setString(4, info.getCountry());
            ps.setString(5, info.getCity());
            ps.setString(6, info.getPhoneNo());
            ps.execute();
            System.out.println("Data inserted");
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception: "+ex.getMessage());
        }
    }
}
