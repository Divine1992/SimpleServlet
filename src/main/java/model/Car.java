package model;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Car.findAll", query = "select c from Car c")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private int cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (cost != car.cost) return false;
        return model != null ? model.equals(car.model) : car.model == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", cost=" + cost +
                '}';
    }
}
