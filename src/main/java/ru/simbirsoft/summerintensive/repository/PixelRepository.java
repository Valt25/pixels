package ru.simbirsoft.summerintensive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.simbirsoft.summerintensive.models.Pixel;


@Repository
public interface PixelRepository extends JpaRepository<Pixel, Long> {
}
