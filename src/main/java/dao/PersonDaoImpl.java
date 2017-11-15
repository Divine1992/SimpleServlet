package dao;

import model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonDaoImpl implements PersonDao{
    private static EntityManager em;

    public static PersonDaoImpl getInstance(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PersonDaoImpl personDao = new PersonDaoImpl();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("LocalHibernate");
        em = factory.createEntityManager();
        return personDao;
    }

    private PersonDaoImpl() {
    }

    public void save(Person p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public void delete(Person p) {
        em.getTransaction().begin();
        em.remove(em.contains(p) ? p : em.merge(p));
        em.getTransaction().commit();
    }

    public List<Person> readAll() {
        em.getTransaction().begin();
        TypedQuery<Person> findPersons = em.createNamedQuery("Person.findAll", Person.class);
        em.getTransaction().commit();
        return findPersons.getResultList();
    }

    public void update(Person p, int id) {
        em.getTransaction().begin();
        Person person = em.find(Person.class, id);
        person.setName(p.getName());
        person.setAge(p.getAge());
        em.getTransaction().commit();
    }
}
