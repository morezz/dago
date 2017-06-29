package dago.repository;

import dago.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * UserInfoRepository
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>, JpaSpecificationExecutor<UserInfo> {
}
