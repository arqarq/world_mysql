package world.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import world.model.Country;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class DisplayLanguages15 {
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()
        ) {
            System.out.println("--- 1 stream API ---");
            Query<Country> q = session.createQuery("FROM Country c", Country.class);
            List<Country> countries = q.getResultList().stream()
                    .filter(country -> country.getCountryLanguages().size() == 1)
                    .collect(Collectors.toList());
            countries.forEach(country -> System.out.println(country.getName()));

            System.out.println("--- 1 HQL ---");
            q = session.createQuery("FROM Country c WHERE size(c.languages) = 1", Country.class);
            q.getResultList().forEach(country -> System.out.println(country.getName()));

            System.out.println("--- 2 HQL ---");
            String lang = "Polish";
            q = session.createQuery("SELECT cl.country " +
                    "FROM CountryLanguage cl " +
                    "WHERE cl.id.language = :lang", Country.class);
            q.setParameter("lang", lang);
            System.out.println(lang + " is being used in " + q.getResultList().size() + " countr(-y/-ies):");
            q.stream().forEach(c -> System.out.println(c.getName()));

            System.out.println("--- 3 HQL ---");
            q = session.createQuery("SELECT cl.country FROM CountryLanguage cl " +
                    "WHERE cl.id.language='Polish' AND cl.isOfficial='T'", Country.class);
            q.stream().forEach(c -> System.out.println(c.getName()));

            System.out.println("--- 4 HQL ---");
            Query<Long> qq = session.createQuery("SELECT count(name) AS cn " +
                            "FROM Country c, CountryLanguage cl " +
//                            "WHERE c.code = cl.country.code " + // 1
//                            "WHERE c.code = cl.id.countryCode " + // 2
//                            "WHERE c.id = cl.id.countryCode " + // 3
                            "WHERE c.id = cl.country.id " + // 4
                            "GROUP BY c.name " +
                            "ORDER BY cn DESC"
//                            + " LIMIT 1" // LIMIT nie działa to wtedy *
                    , Long.class);
            qq.setMaxResults(1); // *
//            qq.setMaxResults(12);
            Long maxLangCountInCountries = qq.uniqueResult();
//            Long maxLangCountInCountries = qq.getSingleResult();
//            List<Long> maxLangCountInCountries = qq.getResultList();

            Query<String> qqq = session.createQuery("SELECT c.name " +
                    "FROM Country c, CountryLanguage cl " +
                    "WHERE c.id = cl.country.id " +
                    "GROUP BY c.name HAVING count(name) = :param ", String.class);
            qqq.setParameter("param", maxLangCountInCountries);
            System.out.println("Najwięcej języków [" + maxLangCountInCountries + "] w:");
            qqq.stream().forEach(System.out::println);
        }
    }
}
