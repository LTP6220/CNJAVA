import java.util.List;

public interface IPhoneDao {
    boolean add(Phone phone);

    Phone get(Long id);

    List<Phone> getAll();

    boolean remove(Long id);

    boolean remove(Phone phone);

    boolean update(Phone phone);
}
