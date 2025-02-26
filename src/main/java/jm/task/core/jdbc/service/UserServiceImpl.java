package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.util.List;


public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl us = new UserDaoJDBCImpl();
    UserDaoHibernateImpl usHib = new UserDaoHibernateImpl();

    public void createUsersTable() {
        usHib.createUsersTable();
        //us.createUsersTable();
    }

    public void dropUsersTable() {
        usHib.dropUsersTable();
        //us.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        //us.saveUser(name, lastName, age);
        usHib.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        usHib.removeUserById(id);
        //us.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return (usHib.getAllUsers());
        //return (us.getAllUsers());
    }

    public void cleanUsersTable() {
        usHib.cleanUsersTable();
        //us.cleanUsersTable();
    }
}
