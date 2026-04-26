package by.pilipuk.repository;

import by.pilipuk.model.dto.GroupCountProjection;
import by.pilipuk.model.entity.Hotel;
import by.pilipuk.exception.ApplicationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import static by.pilipuk.exception.ApplicationExceptionCode.NOT_FOUND_BY_ID;

public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {

    default Hotel findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> ApplicationException.create(NOT_FOUND_BY_ID, id));
    }

    @Override
    @EntityGraph(attributePaths = {"address", "contacts", "arrivalTime"})
    Optional<Hotel> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"address", "contacts", "arrivalTime"})
    List<Hotel> findAll();

    @Override
    @EntityGraph(attributePaths = {"address", "contacts", "arrivalTime"})
    List<Hotel> findAll(Specification<Hotel> spec);

    @Query("SELECT h.brand AS key, COUNT(h) AS count FROM hotels h GROUP BY h.brand")
    List<GroupCountProjection> countGroupByBrand();

    @Query("SELECT h.address.city AS key, COUNT(h) AS count FROM hotels h GROUP BY h.address.city")
    List<GroupCountProjection> countGroupByCity();

    @Query("SELECT h.address.country AS key, COUNT(h) AS count FROM hotels h GROUP BY h.address.country")
    List<GroupCountProjection> countGroupByCountry();
}