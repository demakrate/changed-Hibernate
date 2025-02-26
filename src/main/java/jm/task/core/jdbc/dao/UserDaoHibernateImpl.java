package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users_table " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name TEXT NOT NULL, lastName TEXT NOT NULL, " +
                    "age SMALLINT NOT NULL)";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            session.close();
            System.out.println("Таблица создана");
        } catch (Exception e) {
            System.out.println("Ошибка при работе с базой данных: " + e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users_table";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            System.out.println("Ошибка при работе с базой данных: " + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            Transaction trans = session.beginTransaction();
            session.save(new User(name, lastName, age));
            trans.commit();
            session.close();
            System.out.println("User с именем — ".concat(name).concat(" добавлен в базу данных"));
        } catch (Exception e) {
            System.out.println("Ошибка при работе с базой данных: " + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            String hql = "DELETE User WHERE id = :id";
            Transaction trans = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            trans.commit();
            System.out.println("Пользователь удален");
        } catch (Exception e) {
            System.out.println("Ошибка при работе с базой данных: " + e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = Util.getSession()) {
            String hql = "FROM User";
            Query query = session.createQuery(hql);

            users = query.list();
            if (users.isEmpty()) {
                System.out.println("Пользователи отсутствуют");
            } else {
                for (int i = 0; i < users.size(); i++)
                    System.out.println(users.get(i).toString());
            }
        } catch (Exception e) {
            System.out.println("Ошибка при работе с базой данных: " + e);
        }
        return users;

    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession()) {
            Transaction trans = session.beginTransaction();
            session.createSQLQuery("truncate table users_table").executeUpdate();
            trans.commit();
            System.out.println("Таблица очищена");
        } catch (Exception e) {
            System.out.println("Ошибка при работе с базой данных: " + e);
        }


    }
}
