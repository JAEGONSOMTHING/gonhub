package jg.com.gonhub.users;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);



}
