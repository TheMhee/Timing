/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiraphong
 */
public class db {
    private static int userId;
    Connection conn = null;

    public int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        db.userId = userId;
    }
    public static Connection java__db() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydb.sqlite");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public static void addYearToDb(int year){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydb.sqlite");
            Statement s = conn.createStatement();
            String sql = "ALTER TABLE _table1_old ADD year"+year+" TEXT";
            int n = s.executeUpdate(sql);
//            sql = "INSERT INTO _table1_old ( year"+year+" ) VALUES ( '"+JsonManager.getJSONString(year)+"' )";
////            sql = "insert into _table1_old (year"+year+") values ("+JsonManager.getJSONString(year)+")";
//            n = s.executeUpdate(sql);
            conn.close();
        } catch (ClassNotFoundException |SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
