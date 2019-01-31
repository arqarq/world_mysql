package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import world.model.City;

public class Exercise5 {
    public static void main(String[] args) {
        final String region = "Śląskie";

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session sess = factory.openSession()) {
            Query<City> q = sess.createQuery("SELECT c FROM City c WHERE district = ? " +
                    "ORDER BY name ASC", City.class);
            q.setParameter(0, region);
//            q.setParameter("namee", "name");
            q.stream().forEach(city -> System.out.println(region + ": " + city.getName()));
        }
    }
}
