package be.vdab.toysforboys.entities;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@NamedEntityGraph(
        name = Customer.WITH_COUNTRY,
        attributeNodes = @NamedAttributeNode("country")
)
public class Customer {

    public final static String WITH_COUNTRY ="Customer.withCountry";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "countryId")
    private Country country;

    @Version
    private long version;

    protected Customer(){

    }

    public Customer(String name, Address address, Country country) {
        this.name = name;
        this.address = address;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Country getCountry() {
        return country;
    }

    public long getVersion() {
        return version;
    }
}
