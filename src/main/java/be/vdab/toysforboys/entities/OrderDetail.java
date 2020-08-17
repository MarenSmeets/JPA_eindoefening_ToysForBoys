package be.vdab.toysforboys.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "orderdetails")
@NamedEntityGraph(
        name = OrderDetail.WITH_ORDER_AND_PRODUCT,
        attributeNodes = {
                @NamedAttributeNode("order"),
                @NamedAttributeNode("product")
        }
)
public class OrderDetail implements Serializable {

    private final static long serialVersionUID = 1L;
    public final static String WITH_ORDER_AND_PRODUCT = "OrderDetail.withOrderAndProduct";

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productId")
    private Product product;
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderId")
    private Order order;

    private int ordered;
    private BigDecimal priceEach;

    protected OrderDetail(){

    }

    public OrderDetail(Product product, Order order, int ordered, BigDecimal priceEach) {
        this.product = product;
        this.order = order;
        this.ordered = ordered;
        this.priceEach = priceEach;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public int getOrdered() {
        return ordered;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetail)) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, order);
    }
}
