package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;
    public UserDaoHibernateImpl() {
       this.sessionFactory = new Util().getSesionFactory();
    }

    @Override
    public void createUsersTable() {
        String queryCreateTable = new StringBuilder()
                .append("CREATE TABLE `jmdb_lesson`.`users` ")
                .append("(`id` INT NOT NULL AUTO_INCREMENT, ")
                .append("`name` VARCHAR(45) NOT NULL,")
                .append("`lastName` VARCHAR(45) NOT NULL,")
                .append("`age` INT NOT NULL, PRIMARY KEY (`id`))").toString();

        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(queryCreateTable).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String queryDropTable = "DROP TABLE IF EXISTS users";
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(queryDropTable).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction =  session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
        session.close();
    }
}
