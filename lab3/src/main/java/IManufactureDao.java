import java.util.List;

public interface IManufactureDao {
    boolean add(Manufacture manufacture);

    Manufacture get(Long id);

    List<Manufacture> getAll();

    boolean remove(Long id);

    boolean remove(Manufacture manufacture);

    boolean update(Manufacture manufacture);

}
