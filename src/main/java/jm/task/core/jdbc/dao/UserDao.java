package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import java.util.List;

public interface UserDao {
    default void setSessionFactory(SessionFactory sessionFactory) {
    }

    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
