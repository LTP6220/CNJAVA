package com.example.lab05.DAO;

import com.example.lab05.Model.User;
import com.example.lab05.Repository.Repository;
import com.example.lab05.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO implements Repository<User> {

    public UserDAO() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
    }

    @Override
    public void create(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User created!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public User read(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle the exception or return null as needed
        }
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public void delete(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    public User getUserByUsername(String username) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> readAllUsers() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
