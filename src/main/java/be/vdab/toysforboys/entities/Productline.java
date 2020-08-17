package be.vdab.toysforboys.entities;

import javax.persistence.*;

@Entity
@Table(name = "productlines")
public class Productline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    @Version
    private long version;

    protected Productline(){

    }

    public Productline(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getVersion() {
        return version;
    }
}
