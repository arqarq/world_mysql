package world.model;

import javax.persistence.*;

@Entity
@Table(name = "countrylanguage")
public class CountryLanguage {
    @EmbeddedId
    private CountryLanguageId id;

    @Enumerated(EnumType.STRING)
    @Column(name = "IsOFFicial") // nieważne wielkie-małe litery
    private IsOfficial isOfficial;

    @Column(name = "percentage")
    private Float percentage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CountryCode", insertable = false, updatable = false)
    private Country country;

    public CountryLanguageId getId() {
        return id;
    }

    public void setId(CountryLanguageId id) {
        this.id = id;
    }

    public IsOfficial getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(IsOfficial isOfficial) {
        this.isOfficial = isOfficial;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
