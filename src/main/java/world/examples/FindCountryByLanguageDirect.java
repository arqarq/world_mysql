package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import world.model.Country;

import java.util.logging.Level;

public class FindCountryByLanguageDirect {
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()
        ) {
            Query<Country> query = session.createQuery("SELECT cl.country " +
                    "FROM CountryLanguage cl " +
                    "WHERE cl.id.language = 'French'", Country.class);
            query.stream().forEach(c -> System.out.println(c.getName()));

            query = session.createQuery("SELECT c " +
                    "FROM Country c " +
                    "WHERE c.code = 'POL'", Country.class);
            query.getSingleResult()
                    .getCountryLanguages()
                    .forEach(lang -> System.out.println(lang.getId().getLanguage()));
        }
    }
}
