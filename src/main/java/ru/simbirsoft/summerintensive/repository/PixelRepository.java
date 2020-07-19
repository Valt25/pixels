package ru.simbirsoft.summerintensive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.simbirsoft.summerintensive.models.Pixel;

import java.util.List;

@Repository
public interface PixelRepository extends JpaRepository<Pixel, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM pixels p join (" +
            "SELECT x, y, MAX(created) as created FROM pixels " +
            "GROUP BY x, y" +
                  ") as aggregated ON p.x = aggregated.x and p.y = aggregated.y and p.created = aggregated.created")
    List<Pixel> findAllWithMaxCreated();
}
