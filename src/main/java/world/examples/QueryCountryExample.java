package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import world.model.Country;

import java.util.logging.Level;

public class QueryCountryExample {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session sess = factory.openSession()) {
            Query<Country> q = sess.createQuery("SELECT c FROM Country c " +
                    "WHERE c.capital IS NOT null " +
                    "ORDER BY name ASC", Country.class);
            q.stream().forEach(country -> System.out.println(country.getName() + ": " + country.getCapital().getName()));
        }
    }
}
