/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chiming
 */

//This should be singleton because we need the only instance
public class DBSingleton {
    	
    private static DBSingleton instance = null;
    
    private static Connection conn=null;
    
    private DBSingleton()
    {
             
    }

    public static DBSingleton getInstance()
    {
        if(instance == null)
        {
             instance = new DBSingleton();
        }
        return instance;
    }
    
    public Connection getConnection() throws Exception
    {
        if(instance == null)
        {
            getInstance();
        }
        if(conn == null)
        {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String connectionURL = "jdbc:derby://localhost:1527/EB3";
                conn = DriverManager.getConnection(connectionURL, "mic82", "eb3");
            } catch (ClassNotFoundException clnf) {
                Logger.getLogger(DBSingleton.class.getName()).log(Level.SEVERE, null, clnf);
                throw clnf;
            }
            catch (SQLException sqle)
            {
                Logger.getLogger(DBSingleton.class.getName()).log(Level.SEVERE, null, sqle);
                throw sqle;
            }
        }
        
        return conn;
    }
    
    public void CloseConnection() throws SQLException
    {
        if(instance != null && conn != null)
        {
            conn.close();
        }
    }

}
