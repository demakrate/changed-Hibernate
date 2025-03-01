package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {

    private static UserServiceImpl tableWork = new UserServiceImpl();

    public static void main(String[] args) {
        tableWork.createUsersTable();
        tableWork.saveUser("Dmitry", "Polyakov", (byte) 21);
        tableWork.saveUser("Neymar", "Junior", (byte) 33);
        tableWork.saveUser("Shin", "Lim", (byte) 35);
        tableWork.saveUser("User", "lastname", (byte) 0);
        tableWork.getAllUsers();
        tableWork.cleanUsersTable();
        tableWork.dropUsersTable();
        Util.closeConnection();
    }
}
