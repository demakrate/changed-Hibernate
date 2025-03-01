package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Util {
    private static String url = "jdbc:mysql://localhost:3306/new_schema1";
    private static String user = "root";
    private static String password = "fGhr34kjdv.3f?";
    private static Logger logger = Logger.getLogger("org.hibernate");

    private static Connection con;
    private static SessionFactory sessionFactory;

    private Util() {
    }

    public static Connection getCon() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        return (con);

    }

    public static void closeConnection() {
        sessionFactory.close();
    }

    public static SessionFactory getSessionFactory() {
        logger.setLevel(Level.OFF);
        Properties prop = new Properties();
        Configuration configuration = new Configuration();

        prop.setProperty("hibernate.connection.url", url);
        prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.connection.username", user);
        prop.setProperty("hibernate.connection.password", password);
        configuration.setProperties(prop);
        configuration.addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return (sessionFactory);
    }


}


