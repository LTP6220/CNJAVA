package com.example.lab05.DAO;

import com.example.lab05.Model.Product;
import com.example.lab05.Repository.Repository;
import com.example.lab05.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAO implements Repository<Product> {
    private Session sessionFactory;

    @Override
    public void create(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Product read(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle the exception or return null as needed
        }
    }

    @Override
    public void update(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(product);
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
    public void delete(Product product) {

    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.find(Product.class, id);
            session.delete(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception as needed
        }
    }

    public List<Product> getAll() {
        List<Product> productList = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            productList = session.createQuery("from Product", Product.class).list();
            return productList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
