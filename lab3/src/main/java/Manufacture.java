import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Data
@Entity
@Table(name = "manufacture")
public class Manufacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "Name", nullable = false)
    private String Name;

    @Column(name = "Location", nullable = false)
    private String Location;

    @Column(name = "Employee", nullable = false)
    private Integer Employee;


    public Manufacture() {
    }

    public Manufacture(Long ID, String Name, String Location, Integer Employee) {
        this.ID = ID;
        this.Name = Name;
        this.Location = Location;
        this.Employee = Employee;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Integer getEmployee() {
        return Employee;
    }

    public void setEmployee(Integer employee) {
        Employee = employee;
    }
}
