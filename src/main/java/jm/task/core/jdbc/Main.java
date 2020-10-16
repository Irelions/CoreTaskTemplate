package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Данил", "Новиков" , (byte)32);
        userService.saveUser("Паша", "Волков" , (byte)22);
        userService.saveUser("Денис", "Попов" , (byte)23);
        userService.saveUser("Владимир", "Павлов" , (byte)51);

        userService.getAllUsers().stream().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
