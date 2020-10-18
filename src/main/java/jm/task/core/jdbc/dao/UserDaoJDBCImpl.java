package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;
    private Statement statement;
    private String SQLtableChek = "SHOW TABLES LIKE 'users'";

    public UserDaoJDBCImpl() {
        this.connection = new Util().getConnectionJDBC();
    }

    public void createUsersTable() throws SQLException {
        this.statement = connection.createStatement();
        if(statement.executeUpdate(SQLtableChek) == 0){
            String SQL ="CREATE TABLE `jmdb_lesson`.`users` " +
                    "(`id` INT NOT NULL AUTO_INCREMENT, " +
                    "`name` VARCHAR(45) NOT NULL,"+
                    "`lastName` VARCHAR(45) NOT NULL," +
                    "`age` INT NOT NULL, PRIMARY KEY (`id`))";
            System.out.println("Таблица создана!");
            statement.executeUpdate(SQL);
            statement.close();
        } else {
            statement.close();
        }
    }

    public void dropUsersTable() throws SQLException {
        this.statement = connection.createStatement();
        if(statement.executeUpdate(SQLtableChek) != 0){
            String SQL ="DROP TABLE `jmdb_lesson`.`users`;";
            System.out.println("Таблица удалена!");
            statement.executeUpdate(SQL);
            statement.close();
        } else {
            System.out.println("Таблицы нет в базе");
            statement.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        this.statement = connection.createStatement();
        if(statement.executeUpdate(SQLtableChek) != 0){
            String SQL = String.format("INSERT users (name, lastName, age) VALUES ('%s', '%s', '%d')",name, lastName, (int)age);
            statement.executeUpdate(SQL);
            System.out.println("Данные добавлены в таблицу!");
            statement.close();
        } else {
            statement.close();
        }
    }

    public void removeUserById(long id) throws SQLException {
        this.statement = connection.createStatement();
        if(statement.executeUpdate(SQLtableChek) != 0){
            String SQL = "DELETE FROM users WHERE id=" + id;
            statement.executeUpdate(SQL);
            System.out.println("Данные пользователя по id удалены");
            statement.close();
        } else {
            statement.close();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        this.statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        List<User> list = new ArrayList<>();
        while(resultSet.next()){
            User user = new User(resultSet.getString("name"),
                    resultSet.getString("lastName"),
                    (byte)resultSet.getInt("age"));
            list.add(user);
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        this.statement = connection.createStatement();
        if(statement.executeUpdate(SQLtableChek) != 0){
            String SQL = "DELETE FROM users";
            statement.executeUpdate(SQL);
            System.out.println("Таблица очищена");
            statement.close();
        } else {
            statement.close();
        }
    }
}
