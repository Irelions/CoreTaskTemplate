package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;
    private Statement statement;



    public UserDaoJDBCImpl() {
        this.connection = new Util().getConnection();

    }

    public void createUsersTable() throws SQLException {
        this.statement = connection.createStatement();
        String SQL ="CREATE TABLE `jmdb_lesson`.`users` +" +
                "(`id` INT NOT NULL AUTO_INCREMENT,+" +
                "`name` VARCHAR(45) NOT NULL,"+
                "`lastName` VARCHAR(45) NOT NULL," +
                "`age` INT NOT NULL, PRIMARY KEY (`id`))";
        statement.executeUpdate(SQL);
    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
