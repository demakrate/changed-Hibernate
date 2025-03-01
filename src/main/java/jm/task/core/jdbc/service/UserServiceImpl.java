package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.util.List;


public class UserServiceImpl implements UserService {
    private final UserDaoHibernateImpl usHib = new UserDaoHibernateImpl(Util.getSessionFactory());

    public void setSession(SessionFactory sessionFactory) {
        usHib.setSession(sessionFactory);
    }

    @Override
    public void createUsersTable() {
        usHib.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        usHib.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        usHib.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        usHib.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return (usHib.getAllUsers());
    }

    @Override
    public void cleanUsersTable() {
        usHib.cleanUsersTable();
    }
}
