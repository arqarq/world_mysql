package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.logging.Level;

public class Exercise6GetCountrySizes {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session sess = factory.openSession()
        ) {
            Query<Object[]> q = sess.createQuery("SELECT c.name, size(c.cities) AS city_count " +
                    "FROM Country c " +
//                    "WHERE city_count = 1 " +
//                    "HAVING c.city_count = 1 " +
                    "GROUP BY c " +
                    "ORDER BY city_count ASC", Object[].class);
            q.setMaxResults(85);
            q.stream().forEach(o -> System.out.println("Najmniej miast w " + o[0] + ": " + o[1]));
            q = sess.createQuery("SELECT c.name, size(c.cities) AS city_count " +
                    "FROM Country c GROUP BY c " +
                    "ORDER BY city_count DESC", Object[].class);
            q.setMaxResults(1);
            q.stream().forEach(o -> System.out.println("Najwięcej miast w " + o[0] + ": " + o[1]));
        }
    }
}
