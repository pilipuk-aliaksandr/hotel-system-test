package by.pilipuk.repository;

import by.pilipuk.entity.Amenity;
import by.pilipuk.exeption.ApplicationException;
import org.springframework.data.jpa.repository.JpaRepository;
import static by.pilipuk.exeption.ApplicationExceptionCode.NOT_FOUND_BY_ID;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    default Amenity findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> ApplicationException.create(NOT_FOUND_BY_ID, id));
    }
}