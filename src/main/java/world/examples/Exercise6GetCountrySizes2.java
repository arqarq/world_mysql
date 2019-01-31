package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import java.util.logging.Level;

public class Exercise6GetCountrySizes2 {
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        getCountryWithLeastCities();
    }

    private static void getCountryWithLeastCities() {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()
        ) {
            Query<Long> select = session.createQuery("SELECT count(c.name) AS amount " +
                    "FROM City c, Country cr " +
                    "WHERE c.country.id = cr.id " +
                    "GROUP BY cr.name " +
                    "ORDER BY amount", Long.class);
//            Long value = select.setMaxResults(1).stream().findFirst().get();
            select.setMaxResults(1);
            Long value = select.uniqueResult();

            Query<Object[]> query = session.createQuery("SELECT cr.name, count(c.name) AS amount " +
                    "FROM City c, Country cr " +
//                    "WHERE c.country.code = cr.code " +
                    "WHERE c.country.id = cr.id " +
                    "GROUP BY cr.name HAVING count(c.name) = :min " +
                    "ORDER BY cr.name", Object[].class);
            query.setParameter("min", value);
            query.stream().forEach(o -> System.out.println(o[0] + ": " + o[1]));
            System.out.println("Total countries with min " + value + " city(-ies): " + query.getResultList().size());
        }
    }
}
