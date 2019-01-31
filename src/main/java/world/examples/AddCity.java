package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;
import world.model.City;
import world.model.Country;

import java.util.logging.Level;

public class AddCity {
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session sess = factory.openSession()
        ) {
            City newCity = new City();
            newCity.setName("Grunwald");
            newCity.setDistrict("Śląskie");

            Country poland = sess.load(Country.class, "POL");
            newCity.setCountry(poland);
            poland.getCities().add(newCity);

            Transaction tx = sess.beginTransaction();
            sess.save(newCity);
            tx.commit();
        }
    }
}
