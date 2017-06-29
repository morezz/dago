package dago.repository;

import dago.model.Kind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * KindRepository
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface KindRepository extends JpaRepository<Kind, Long>, JpaSpecificationExecutor<Kind> {
}
