import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PhoneDAO implements IPhoneDao {

    private final SessionFactory sessionFactory;

    public PhoneDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create a new phone record
    @Override
    public boolean add(Phone phone) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(phone);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Phone get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Phone.class, id);
        }
    }

    @Override
    public List<Phone> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Phone> query = session.createQuery("FROM Phone", Phone.class);
            return query.list();
        }
    }

    @Override
    public boolean remove(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Phone phone = session.get(Phone.class, id);
            if (phone != null) {
                session.delete(phone);
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(Phone phone) {
        return remove(phone.getID());
    }

    @Override
    public boolean update(Phone phone) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(phone);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Phone getPhoneWithHighestPrice() {
        try (Session session = sessionFactory.openSession()) {
            Query<Phone> query = session.createQuery("FROM Phone ORDER BY Price DESC", Phone.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }

    // Method to get a list of phones sorted by country name, if two phones have the same country, sort descending by price
    public List<Phone> getPhonesSortedByCountryAndPrice() {
        try (Session session = sessionFactory.openSession()) {
            Query<Phone> query = session.createQuery("FROM Phone ORDER BY Country ASC, Price DESC", Phone.class);
            return query.list();
        }
    }

    // Method to check if there is a phone Priced above 50 million VND
    public boolean hasPhoneAbove50Million() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Phone WHERE Price > 50000000", Long.class);
            return query.uniqueResult() > 0;
        }
    }

    // Method to get the first phone in the list that meets the criteria: has the color 'Pink' and costs over 15 million
    public Phone getFirstPinkPhoneAbove15Million() {
        try (Session session = sessionFactory.openSession()) {
            Query<Phone> query = session.createQuery("FROM Phone WHERE Color = 'Pink' AND Price > 15000000", Phone.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }


}
