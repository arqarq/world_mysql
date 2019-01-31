package world.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import world.model.City;


public class RepositoryTest {

    public static void main(String[] args) {
        try(SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            CityRepository repo = new CityRepository(factory);
            Specification<City> specs = new CityByFirstLetter('P');
            repo.findBySpecification(specs).forEach(c -> System.out.println(c.getName()));
        }
    }
}
