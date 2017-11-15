package dao;

import model.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarDaoImpl implements CarDao {
    private static EntityManager em;

    public static CarDao getInstance(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        CarDao carDao = new CarDaoImpl();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("LocalHibernate");
        em = factory.createEntityManager();
        return carDao;
    }

    private CarDaoImpl() {
    }

    public void save(Car c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();

    }

    public void delete(Car c) {
        em.getTransaction().begin();
        em.remove(em.contains(c)? c:em.merge(c));
        em.getTransaction().commit();
    }

    public List<Car> readAll() {
        em.getTransaction().begin();
        TypedQuery<Car> cars = em.createNamedQuery("Car.findAll", Car.class);
        em.getTransaction().commit();
        return cars.getResultList();
    }

    public void update(Car c) {
        em.getTransaction().begin();
        Car car = em.find(Car.class, c.getId());
        car.setModel(c.getModel());
        car.setCost(c.getCost());
        em.getTransaction().commit();
    }
}
