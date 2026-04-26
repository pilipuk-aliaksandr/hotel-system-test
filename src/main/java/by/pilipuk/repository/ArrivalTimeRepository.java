package by.pilipuk.repository;

import by.pilipuk.model.entity.ArrivalTime;
import by.pilipuk.exception.ApplicationException;
import org.springframework.data.jpa.repository.JpaRepository;
import static by.pilipuk.exception.ApplicationExceptionCode.NOT_FOUND_BY_ID;

public interface ArrivalTimeRepository extends JpaRepository<ArrivalTime, Long> {

    default ArrivalTime findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> ApplicationException.create(NOT_FOUND_BY_ID, id));
    }

}