package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import world.model.CountryLanguage;

public class FindLanguagesExample {

    public static void main(String[] args) {
        try(SessionFactory factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession()) {
            Query<CountryLanguage> query = session.createQuery(
                    "FROM CountryLanguage cl WHERE cl.id.language='French'", CountryLanguage.class);
            query.stream().forEach(cl -> System.out.println(cl.getId().getCountryCode()));

        }
    }
}
