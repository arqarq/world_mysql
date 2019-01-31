package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import world.model.CityNameDistrict;

public class QueryCustomResult {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session sess = factory.openSession()) {
            Query<CityNameDistrict> q = sess.createQuery(
                    "SELECT new world.model.CityNameDistrict(c.name, c.district) FROM City c ORDER BY c.name",
                    CityNameDistrict.class);
            q.setMaxResults(10);
            q.stream().forEach(o -> System.out.println(o.getName() + " " + o.getDistrict()));
        }
    }
}
