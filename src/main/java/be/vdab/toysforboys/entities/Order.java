package be.vdab.toysforboys.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@NamedEntityGraph(
        name = Order.WITH_CUSTOMER,
        attributeNodes = @NamedAttributeNode("customer"))

public class Order {

    public final static String WITH_CUSTOMER = "Order.withCustomer";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate ordered;
    private LocalDate required;
    private LocalDate shipped;
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Version
    private long version;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    protected Order(){

    }

    public Order(LocalDate ordered, LocalDate required, LocalDate shipped, String comments, Customer customer, Status status) {
        this.ordered = ordered;
        this.required = required;
        this.shipped = shipped;
        this.comments = comments;
        this.customer = customer;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public LocalDate getOrdered() {
        return ordered;
    }

    public LocalDate getRequired() {
        return required;
    }

    public LocalDate getShipped() {
        return shipped;
    }

    public String getComments() {
        return comments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Status getStatus() {
        return status;
    }

    public Set<OrderDetail> getOrderDetails() {
        return Collections.unmodifiableSet(orderDetails);
    }

    public long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(ordered, order.ordered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordered);
    }
}
