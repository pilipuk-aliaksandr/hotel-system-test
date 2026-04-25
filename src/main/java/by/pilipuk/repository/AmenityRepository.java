package by.pilipuk.repository;

import by.pilipuk.entity.Address;
import by.pilipuk.entity.Amenity;
import by.pilipuk.exeption.ValidationException;
import org.springframework.data.jpa.repository.JpaRepository;

import static by.pilipuk.model.enums.ValidationCode.NOT_FOUND_BY_ID;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    default Amenity findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> ValidationException.create(NOT_FOUND_BY_ID, id));
    }
}