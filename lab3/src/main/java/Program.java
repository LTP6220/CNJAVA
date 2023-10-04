import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Hello world!
 */
public class Program {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        PhoneDAO phoneDAO = new PhoneDAO(sessionFactory);
        Phone phone = new Phone();
        phone.setName("Iphone 15 PRoMax");
        phone.setColor("Black");
        phone.setPrice(1000);
        phone.setCountry("USA");
        phone.setQuantity(10);
        phoneDAO.add(phone);
        System.out.println(phoneDAO.get(4L));
        System.out.println(phoneDAO.getAll());
        phoneDAO.remove(4L);
        Phone phone1 = phoneDAO.get(5L);
        phoneDAO.remove(phone1);
        phone1.setPrice(9000000);
        phoneDAO.update(phone1);

        ManufactureDAO manufactureDAO = new ManufactureDAO(sessionFactory);
        Manufacture manufacture = new Manufacture();
        manufacture.setName("Apple");
        manufacture.setLocation("USA");
        manufacture.setEmployee(100000);
        manufactureDAO.add(manufacture);
        System.out.println(manufactureDAO.get(2L));
        System.out.println(manufactureDAO.getAll());
        manufactureDAO.remove(2L);
        Manufacture manufacture1 = manufactureDAO.get(3L);
        manufactureDAO.remove(manufacture1);
        manufacture1.setEmployee(100000);
        manufactureDAO.update(manufacture1);

        System.out.println(phoneDAO.getPhoneWithHighestPrice());
        System.out.println(phoneDAO.getPhonesSortedByCountryAndPrice());
        System.out.println(phoneDAO.hasPhoneAbove50Million());
        System.out.println(phoneDAO.getFirstPinkPhoneAbove15Million());


        System.out.println(manufactureDAO.areAllManufacturersAbove100Employees());
        System.out.println(manufactureDAO.getTotalEmployeesOfManufactures());
        System.out.println(manufactureDAO.getLastManufactureBasedInUS());
        session.close();
        sessionFactory.close();

    }
}
