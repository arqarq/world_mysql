package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import world.model.City;

public class QueryCitiesExample {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session sess = factory.openSession()) {
            Query<City> q = sess.createQuery("SELECT c FROM City c", City.class);
//            q.setMaxResults(10);
//            q.stream().forEach(city -> System.out.println(city.getId() + " " + city.getName()));
            q.stream().forEach(city -> System.out.println(city.getName() + " " + city.getCountry().getName()));
        }
    }
}
