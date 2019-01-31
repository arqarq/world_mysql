package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.logging.Level;

public class GetCountrySizes {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session sess = factory.openSession()
        ) {
            Query<Object[]> q = sess.createQuery("SELECT c.name, size(c.cities) " +
                    "FROM Country c GROUP BY c ORDER BY name ASC", Object[].class);
            q.stream().forEach(o -> System.out.println(o[0] + ": " + o[1]));
        }
    }
}
