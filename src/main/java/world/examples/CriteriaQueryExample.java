package world.examples;

import world.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CriteriaQueryExample {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Country> q = cb.createQuery(Country.class);
            Root<Country> root = q.from(Country.class);
            q.select(root);

            q.where(cb.and(
                    cb.like(root.get("name"), "P%"),
                    cb.like(root.get("name"), "%n")));
            // SELECT c FROM Country c WHERE c.name LIKE 'P%' AND c.name LIKE '%n'
            Query<Country> realQuery = session.createQuery(q);
            realQuery.setMaxResults(10)
                    .stream()
                    .forEach(c -> System.out.println(c.getName()));
        }
    }
}
