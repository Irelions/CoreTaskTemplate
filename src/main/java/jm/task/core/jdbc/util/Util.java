package jm.task.core.jdbc.util;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost/jmdb_lesson?useLegacyDatetimeCode=false&serverTimezone=UTC";
    Connection connection;

    public Util(){

    }

    public Connection getConnection() {
        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            this.connection = connection;
            if (!connection.isClosed()) {
                System.out.println("Соединение к базе установлено!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
