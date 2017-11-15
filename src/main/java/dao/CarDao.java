package dao;

import model.Car;

import java.util.List;

public interface CarDao {
    void save(Car c);
    void delete(Car c);
    List<Car> readAll();
    void update(Car c);
}
