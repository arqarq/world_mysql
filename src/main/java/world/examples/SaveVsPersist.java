package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import world.model.City;
import world.model.Country;

public class SaveVsPersist {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            City grunwald = session.load(City.class, 4105);
            grunwald.setDistrict("Wielkopolskie");
            session.evict(grunwald);
            Transaction tx = session.beginTransaction();
//            session.persist(grunwald);
            session.save(grunwald); // persist rzuciłoby wyjątkiem
            tx.commit();
        }
    }
}
