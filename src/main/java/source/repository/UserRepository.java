package source.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import source.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username LIKE CONCAT('%', :searchKey, '%')")
    Page<User> searchUsers(String searchKey, Pageable pageable);

    boolean existsByUsername(String username);

}
