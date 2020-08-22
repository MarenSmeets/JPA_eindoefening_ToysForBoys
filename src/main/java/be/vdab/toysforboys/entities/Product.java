package be.vdab.toysforboys.entities;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
@NamedEntityGraph(
        name = Product.WITH_PRODUCTLINE,
        attributeNodes = @NamedAttributeNode("productline")
)
public class Product {

    public final static String WITH_PRODUCTLINE = "Product.withProductline";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String scale;
    private String description;
    private int inStock;
    private int inOrder;
    @NumberFormat(pattern = "0.0")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productlineId")
    private Productline productline;

    @Version
    private long version;

    protected Product(){

    }

    public Product(String name, String scale, String description, int inStock, int inOrder, BigDecimal price, Productline productline) {
        this.name = name;
        this.scale = scale;
        this.description = description;
        this.inStock = inStock;
        this.inOrder = inOrder;
        this.price = price;
        this.productline = productline;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScale() {
        return scale;
    }

    public String getDescription() {
        return description;
    }

    public int getInStock() {
        return inStock;
    }

    public int getInOrder() {
        return inOrder;
    }

    public BigDecimal getPrice() {
        return price.setScale(1);
    }

    public Productline getProductline() {
        return productline;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name.toUpperCase(), product.name.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toUpperCase());
    }
}
