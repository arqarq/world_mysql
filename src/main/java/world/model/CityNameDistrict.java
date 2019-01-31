package world.model;

public class CityNameDistrict {

    private String name;
    private String district;

    public CityNameDistrict(String name, String district) {
        this.name = name;
        this.district = district; }

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