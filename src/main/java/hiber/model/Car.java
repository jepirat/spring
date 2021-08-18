package hiber.model;
import javax.persistence.*;


@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "series")
    public int series;
    @Column(name = "brand")
    private String brand;
    @OneToOne (mappedBy="car")
    private User owner;

    public Car() {
    }

    public Car(int series, String brand) {
        this.series = series;
        this.brand = brand;
    }



    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String model) {
        this.brand = model;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Id=" + Id +
                ", series=" + series +
                ", brand='" + brand + '\'' +
                ", owner=" + owner.getFirstName() +
                '}';
    }
}
