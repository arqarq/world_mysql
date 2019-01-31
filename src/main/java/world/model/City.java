package world.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // wartość jest generowana - w bazie danych, niech sobie robi;
    @Column(name = "ID")                                // może też hibernate robić sam
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "District")
    private String district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryCode")
    private Country country;

    public City() { // domyślny konstruktor musi być w klasach modelujących encję
    }

    public Country getCountry() {
        return country;
    }

    public City setCountry(Country country) {
        this.country = country;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
