package by.pilipuk.repository;

import by.pilipuk.model.dto.GroupCountProjection;
import by.pilipuk.model.entity.Amenity;
import by.pilipuk.exception.ApplicationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static by.pilipuk.exception.ApplicationExceptionCode.NOT_FOUND_BY_ID;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    default Amenity findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> ApplicationException.create(NOT_FOUND_BY_ID, id));
    }

    @Query("SELECT a.name AS key, COUNT(a) AS count FROM amenities a GROUP BY a.name")
    List<GroupCountProjection> countGroupByName();
}