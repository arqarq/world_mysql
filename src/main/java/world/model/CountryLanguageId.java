package world.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CountryLanguageId implements Serializable {
    @Column(name = "CountryCode")
    private String countryCode;

    @Column(name = "Language")
    private String language;

    public CountryLanguageId() {
    }

    public CountryLanguageId(String countryCode, String language) {
        this.countryCode = countryCode;
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryLanguageId)) return false;
        CountryLanguageId id = (CountryLanguageId) o;
        return Objects.equals(id.getCountryCode(), getCountryCode()) &&
                Objects.equals(id.getLanguage(), getLanguage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountryCode(), getLanguage());
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
