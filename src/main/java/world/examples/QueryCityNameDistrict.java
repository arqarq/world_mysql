package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class QueryCityNameDistrict {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session sess = factory.openSession()) {
            Query<Object[]> q = sess.createQuery("SELECT c.name, c.district FROM City c", Object[].class);
            q.setMaxResults(10);
            q.stream().forEach(o -> System.out.println(o[0] + " " + o[1]));
        }
    }
}
