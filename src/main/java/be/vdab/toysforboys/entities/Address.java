package be.vdab.toysforboys.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Objects;


@Embeddable
@Access(AccessType.FIELD)
public class Address {

    private String streetAndNumber;
    private String city;
    private String state;
    private String postalCode;

    protected Address(){

    }

    public Address(String streetAndNumber, String city, String state, String postalCode) {
        this.streetAndNumber = streetAndNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(streetAndNumber.toUpperCase(), address.streetAndNumber.toUpperCase()) &&
                Objects.equals(city.toUpperCase(), address.city.toUpperCase()) &&
                Objects.equals(state.toUpperCase(), address.state.toUpperCase()) &&
                Objects.equals(postalCode.toUpperCase(), address.postalCode.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetAndNumber.toUpperCase(), city.toUpperCase(), state.toUpperCase(), postalCode.toUpperCase());
    }

    @Override
    public String toString() {
        return streetAndNumber + ", " + city + ", " + state + ", " + postalCode;
    }
}

