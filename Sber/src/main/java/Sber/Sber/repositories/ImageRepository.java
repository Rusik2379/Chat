package Sber.Sber.repositories;

import Sber.Sber.models.Image;
import Sber.Sber.models.Realty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Realty> findByRealtyId(Long id);

    List<ImageRepository.ImageProjection> findByRealty(Realty realty); // Changed method parameter to Realty

    interface ImageProjection {
        Long getId();
    }
}
