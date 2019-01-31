package world.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "Code")
    private String code;

    @Column(name = "Name")
    private String name;

    @Column(name = "Code2")
    private String code2;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Capital")
    private City capital;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<City> cities;

    @OneToMany(mappedBy = "country")
    private List<CountryLanguage> languages;

    public Country() {
    }

    public List<CountryLanguage> getCountryLanguages() {
        return languages;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
