package world.repository;

import world.model.City;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CityByFirstLetter implements Specification<City> {
    private final Character letter;

    CityByFirstLetter(Character letter) {
        this.letter = letter;
    }

    @Override
    public Predicate toPredicate(CriteriaBuilder cb, Root<City> root) {
        return cb.like(root.get("name"), this.letter + "%");
    }
}
