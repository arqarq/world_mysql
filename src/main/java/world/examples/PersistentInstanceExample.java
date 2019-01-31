package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import world.model.City;

public class PersistentInstanceExample {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            City grunwald = session.load(City.class, 4105);
            session.beginTransaction();
            grunwald.setDistrict("Śląskiee");
            session.getTransaction().commit();
        }
    }
}
