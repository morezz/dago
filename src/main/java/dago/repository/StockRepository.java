package dago.repository;

import dago.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * StockRepository
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface StockRepository extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock> {
}
