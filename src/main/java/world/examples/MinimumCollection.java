package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class MinimumCollection {

    public static void main(String[] args) {
        try(SessionFactory factory = new Configuration().configure().buildSessionFactory();
            Session sess = factory.openSession()) {
            Query<Integer> q = sess.createQuery(
                    "SELECT min(x) FROM (SELECT count(c.cities) as x FROM Country c GROUP BY c) GROUP BY x");
            q.stream().forEach(System.out::println);
        }
    }
}
