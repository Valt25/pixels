package ru.simbirsoft.summerintensive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.simbirsoft.summerintensive.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
