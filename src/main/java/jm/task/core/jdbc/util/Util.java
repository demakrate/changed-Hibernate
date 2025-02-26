package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.*;
import java.sql.*;


public class Util implements AutoCloseable {
    private static String url = "jdbc:mysql://localhost:3306/new_schema1";
    private static String user = "root";
    private static String password = "fGhr34kjdv.3f?";
    private static Logger logger = Logger.getLogger("org.hibernate");

    private static Connection con;
    private static Session session;

    public static Connection getCon() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        return (con);

    }

    public void close() throws SQLException {
        con.close();
        session.close();
    }

    public static Session getSession() {
        logger.setLevel(Level.OFF);
        SessionFactory sessionFactory;
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
        session = sessionFactory.openSession();
        return (session);
    }


}


