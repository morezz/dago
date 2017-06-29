package dago.repository;

import dago.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * OrderItemRepository
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem> {
}
