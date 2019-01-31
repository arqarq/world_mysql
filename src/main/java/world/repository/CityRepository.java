package world.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import world.model.City;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CityRepository {
    private SessionFactory factory;

    public CityRepository(SessionFactory factory) {
        this.factory = factory;
    }

    public List<City> findBySpecification(Specification<City> specs) {
        try (Session sess = factory.openSession()
        ) {
            CriteriaBuilder cb = sess.getCriteriaBuilder();
            CriteriaQuery<City> cq = cb.createQuery(City.class);
            Root<City> root = cq.from(City.class);
            cq.select(root);
            cq.where(specs.toPredicate(cb, root));
            return sess.createQuery(cq).getResultList();
        }
    }
}
