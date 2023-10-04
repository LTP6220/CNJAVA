import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Data
@Entity
@Table(name = "mobilephone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", insertable = false, updatable = false)
    private Long ID;

    @Column(name = "Name", nullable = false, length = 128)
    private String Name;

    @Column(name = "Price", nullable = false)
    private Integer Price;

    @Column(name = "Color", nullable = false)
    private String Color;

    @Column(name = "Country", nullable = false)
    private String Country;

    @Column(name = "Quantity", nullable = false)
    private Integer Quantity;

    @ManyToOne
    @JoinColumn(name = "ManufactureID")
    private Manufacture manufacturer;

    public Phone() {
    }

    public Phone(Long ID, String Name, Integer Price, String Color, String Country, Integer Quantity) {
        this.ID = ID;
        this.Name = Name;
        this.Price = Price;
        this.Color = Color;
        this.Country = Country;
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Price='" + Price + '\'' +
                ", Color='" + Color + '\'' +
                ", Country='" + Country + '\'' +
                ", Quantity='" + Quantity + '\'' +
                '}';
    }


    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public void setColor(String color) {
        Color = color;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

}
