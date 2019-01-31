package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import world.model.Country;
import world.model.CountryLanguage;

public class FindLanguageFullCountry {
    public static void main(String[] args) {
        try(SessionFactory factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession()) {
            Query<String> query = session.createQuery(
                    "SELECT c.name FROM CountryLanguage cl JOIN Country c ON cl.id.countryCode=c.code " +
                            "WHERE cl.id.language='French'", String.class);
            query.stream().forEach(System.out::println);
        }
    }
}
