package dago.repository;

import dago.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ProductInfoRepository
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long>, JpaSpecificationExecutor<ProductInfo> {
}
