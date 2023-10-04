import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ManufactureDAO implements IManufactureDao {
    private final SessionFactory sessionFactory;

    public ManufactureDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean add(Manufacture manufacture) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(manufacture);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Manufacture get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Manufacture.class, id);
        }
    }

    @Override
    public List<Manufacture> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Manufacture> query = session.createQuery("FROM Manufacture", Manufacture.class);
            return query.list();
        }
    }

    @Override
    public boolean remove(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id);
            if (manufacture != null) {
                session.delete(manufacture);
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(Manufacture manufacture) {
        return remove(manufacture.getID());

    }

    @Override
    public boolean update(Manufacture manufacture) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(manufacture);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to check whether all manufacturers have more than 100 employees
    public boolean areAllManufacturersAbove100Employees() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(*) FROM Manufacture WHERE Employee < 100",
                    Long.class
            );
            return query.uniqueResult() == 0;
        }
    }

    // Method that returns the sum of all employees of the manufacturers
    public long getTotalEmployeesOfManufactures() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT SUM(Employee) FROM Manufacture",
                    Long.class
            );
            Long result = query.uniqueResult();
            return result != null ? result : 0;
        }
    }

    // Method that returns the last manufacturer in the list of manufacturers based in the US
    public Manufacture getLastManufactureBasedInUS() {
        try (Session session = sessionFactory.openSession()) {
            Query<Manufacture> query = session.createQuery(
                    "FROM Manufacture WHERE Location = 'US' ORDER BY id DESC",
                    Manufacture.class
            );
            query.setMaxResults(1);
            Manufacture manufacturer = query.uniqueResult();
            if (manufacturer == null) {
                throw new RuntimeException("No manufacturer based in the US found.");
            }
            return manufacturer;
        }
    }
    
}
