package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import source.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
