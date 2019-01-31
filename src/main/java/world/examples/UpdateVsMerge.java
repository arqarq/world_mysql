package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import world.model.City;

public class UpdateVsMerge {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            City grunwald = session.load(City.class, 4108);
            grunwald.setDistrict("Małopolskie");
            session.evict(grunwald);
            Transaction tx = session.beginTransaction();
            session.update(grunwald); // grunwald jest teraz znowu w stanie persistent
            tx.commit();

            grunwald.setDistrict("Śląskiee");
            session.evict(grunwald);
            tx = session.beginTransaction();
            City grunwald2 = (City) session.merge(grunwald);
            tx.commit();
        }
    }
}
