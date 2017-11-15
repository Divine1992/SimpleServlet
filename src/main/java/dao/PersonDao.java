package dao;

import model.Person;

import java.util.List;

public interface PersonDao {
    void save(Person p);
    void delete(Person p);
    List<Person> readAll();
    void update(Person p, int id);
}
