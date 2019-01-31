package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import world.model.Country;

public class GetAllCountriesExample {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            Query<Country> query = session.createQuery("FROM Country c", Country.class);
            query.setMaxResults(10);
            query.stream().forEach(c -> System.out.println(c.getName()));
        }
    }
}
