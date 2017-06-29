package dago.repository;

import dago.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * OrderRepository
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}
