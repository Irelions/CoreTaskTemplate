package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    private final static String TRUE = "true";
    Connection connection;

    public Util(){
    }

    public Connection getConnectionJDBC() throws HibernateException {
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

    public SessionFactory getSesionFactory () {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.connection.user", USER)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", TRUE)
                .addAnnotatedClass(User.class)
                ;



        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        return configuration.buildSessionFactory(serviceRegistry);

    }
}
