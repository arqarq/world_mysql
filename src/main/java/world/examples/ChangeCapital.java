package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import world.model.City;
import world.model.Country;

import java.util.logging.Level;

public class ChangeCapital {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session sess = factory.openSession()
        ) {
            String newCapital = "Katowice";

            Query<City> city = sess.createQuery("SELECT c FROM City c WHERE name LIKE :cityname", City.class);
            city.setParameter("cityname", newCapital);

            Country country = city.uniqueResult().getCountry();
            country.setCapital(city.uniqueResult());

            Transaction tx = sess.beginTransaction();
            sess.save(country);
//            tx.rollback();
            tx.commit();
        }
    }
}
