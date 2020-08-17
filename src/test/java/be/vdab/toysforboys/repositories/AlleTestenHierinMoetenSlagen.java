package be.vdab.toysforboys.repositories;

import be.vdab.toysforboys.entities.Customer;
import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.entities.OrderDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

//@Disabled
//@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql("/insertTestData.sql")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaOrderRepository.class)
public class AlleTestenHierinMoetenSlagen extends AbstractTransactionalJUnit4SpringContextTests {

    private final static String ORDERS = "toysforboys.orders";

    @Autowired
    private JpaOrderRepository repository;

    private Order order;
    private Customer customer;
    private Set<OrderDetail> orderDetails;

    private Long idVanTestOrder() {
        return super.jdbcTemplate.queryForObject("select id from toysforboys.orders where comments = 'test'", Long.class);
    }

    @BeforeEach
    public void init() {
        order = repository.findById(idVanTestOrder()).orElseThrow(RuntimeException::new);
        customer = order.getCustomer();
        orderDetails = order.getOrderDetails();
    }

    @Test
    void idVanTestOrderIs9999(){
        assertThat(idVanTestOrder()).isEqualTo(9999);
    }

    @Test
    void findById(){
        assertThat(repository.findById(idVanTestOrder()).get().getComments()).isEqualTo("test");
    }

//    @Test
//    public void isOrderCorrect() {
//        assertThat(order.getComments()).isEqualTo("test");
//        assertThat(order.getStatus()).isEqualTo(Status.WAITING);
//        assertThat(order.getId()).isEqualTo(9999);
//        assertThat(order.getCustomer()).isNotNull();
//    }
//
//    @Test
//    public void isCustomerCorrect() {
//        assertThat(customer.getId()).isEqualTo(9999);
//
//        Address address = new Address("Straat 18", "Antwerpen", "Antwerpen", "2000");
//        assertThat(customer.getAddress()).isEqualTo(address);
//        assertThat(customer.getName()).isEqualTo("Jos");
//        assertThat(customer.getCountry().getName()).isEqualTo("België");
//    }
//
//    @Test
//    public void isOrderDetailCorrect() {
//        assertThat(orderDetails).hasSize(1);
//        OrderDetail orderDetail = orderDetails.stream().findFirst().orElseThrow(RuntimeException::new);
//
//        Product product = orderDetail.getProduct();
//        assertThat(product).isNotNull();
//        assertThat(product.getName()).isEqualTo("VR-bril");
//        assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(449.5));
//
//        Productline productline = product.getProductline();
//        assertThat(productline).isNotNull();
//        assertThat(productline.getName()).isEqualTo("VR");
//    }
//
//    @Test
//    public void findUnshipped() {
//        List<Order> orders = repository.findUnshipped();
//        int numberOfOrders = super.countRowsInTableWhere(ORDERS, "status not in ('SHIPPED','CANCELLED')");
//        assertThat(numberOfOrders).isEqualTo(orders.size());
//        orders.stream().reduce((previousOrder, order) -> {
//            assertThat(order.getStatus()).isNotEqualByComparingTo(Status.SHIPPED);
//            assertThat(order.getStatus()).isNotEqualByComparingTo(Status.CANCELLED);
//            assertThat(order.getOrdered()).isAfterOrEqualTo(previousOrder.getOrdered());
//            return order;
//        });
//    }
}