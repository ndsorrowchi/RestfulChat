/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;


/**
 *
 * @author chiming
 */
public class MyContextListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(MyContextListener.class.getName());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("OnInitialized() called");
        try {
            DBSingleton.getInstance().getConnection();
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            
            log.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("OnDestroyed() called");
        try {
            DBSingleton.getInstance().CloseConnection();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }
    
}
