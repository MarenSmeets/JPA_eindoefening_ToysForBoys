package be.vdab.toysforboys.repositories;

import be.vdab.toysforboys.entities.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrderRepository implements OrderRepository {

    private final EntityManager manager;

    public JpaOrderRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Order> findById(long id) {
//        throw new UnsupportedOperationException();
        return Optional.ofNullable(manager.find(Order.class, id));
    }

    @Override
    public List<Order> findUnshipped() {
//        throw new UnsupportedOperationException();
        return manager
                .createNamedQuery("Order.findUnshipped", Order.class )
                .setHint("javax.persistence.loadgraph", manager.createEntityGraph(Order.WITH_CUSTOMER))
                .getResultList();
    }
}
