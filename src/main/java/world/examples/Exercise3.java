package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import world.model.Country;

public class Exercise3 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session sess = factory.openSession()) {
            Query<Country> q = sess.createQuery("SELECT c FROM Country c", Country.class);
            q.setMaxResults(10);
            q.stream().forEach(country -> System.out.println(country.getCode() + " " + country.getName()));
        }
    }
}
