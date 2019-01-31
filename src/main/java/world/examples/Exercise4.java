package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import world.model.Country;

public class Exercise4 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session sess = factory.openSession()) {
            Query<Country> q = sess.createQuery("SELECT c FROM Country c", Country.class);
            q.stream().forEach(country -> System.out.println(country.getCode2() + " " + country.getName()));
        }
    }
}
