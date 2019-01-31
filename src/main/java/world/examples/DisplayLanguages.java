package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import world.model.Country;

public class DisplayLanguages {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()
        ) {
            Country rus = session.load(Country.class, "RUS");
            System.out.println("--- RUS");
            rus.getCountryLanguages().forEach(lang -> System.out.println(lang.getId().getLanguage()));

            System.out.println("--- POl");
            Query<Country> q = session.createQuery("SELECT c FROM Country c WHERE c.code = 'POL'", Country.class);
            rus = q.getSingleResult();
            rus.getCountryLanguages().forEach(lang -> System.out.println(lang.getId().getLanguage()));
        }
    }
}
