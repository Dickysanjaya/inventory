/*
 * -------------------------------------------------------------------
 *     asep  - e_mail = aasseepp@gmail.com
 * -------------------------------------------------------------------
 */
package inventori.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Puji
 */
public class DatabaseUtilities {
    private static Connection connection;

    public static Connection getConnection() {
        
        if(connection  == null){
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan","root","");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        return connection;
        
    }
    
    
}
