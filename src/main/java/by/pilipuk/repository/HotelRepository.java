package by.pilipuk.repository;

import by.pilipuk.model.entity.Hotel;
import by.pilipuk.exeption.ApplicationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import static by.pilipuk.exeption.ApplicationExceptionCode.NOT_FOUND_BY_ID;

public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {

    default Hotel findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> ApplicationException.create(NOT_FOUND_BY_ID, id));
    }
}