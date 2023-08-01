package ar.com.egomusic.basiccrudmaven.controller;

import ar.com.egomusic.basiccrudmaven.persistence.FriendDAO;
import ar.com.egomusic.basiccrudmaven.persistence.FriendPersistFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;


public class StartUpListener implements ServletContextListener {

    private FriendDAO friendDAO;

    @Override
    public void contextInitialized(ServletContextEvent evt) {
        String url, driver, user, password, persistenceMechanism;
        ServletContext context = evt.getServletContext();
        url = context.getInitParameter("databaseURL");
        driver = context.getInitParameter("databaseDriver");
        user = context.getInitParameter("databaseUser");
        password = context.getInitParameter("databasePassword");
        persistenceMechanism = context.getInitParameter("persistenceMechanism");
        friendDAO = FriendPersistFactory.getFriendDAO(persistenceMechanism);
        boolean ok = friendDAO.setUp(url, driver, user, password);
        if (!ok) {
            context.setAttribute("persistenceMechanism", "none");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent evt) {
        boolean ok = friendDAO.disconnect();
        if (!ok) {
            Logger.getLogger(StartUpListener.class.getName()).log(Level.SEVERE,
                    "Driver de banco de dados não encontrado");
        }
    }
}
