package ru.simbirsoft.summerintensive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.simbirsoft.summerintensive.models.RollbackTime;

public interface RollbackTimeRepository extends JpaRepository<RollbackTime, Long> {
    long count();
}
