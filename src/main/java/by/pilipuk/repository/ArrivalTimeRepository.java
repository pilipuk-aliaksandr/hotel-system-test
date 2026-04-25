package by.pilipuk.repository;

import by.pilipuk.entity.ArrivalTime;
import by.pilipuk.entity.Contact;
import by.pilipuk.exeption.ValidationException;
import org.springframework.data.jpa.repository.JpaRepository;

import static by.pilipuk.model.enums.ValidationCode.NOT_FOUND_BY_ID;

public interface ArrivalTimeRepository extends JpaRepository<ArrivalTime, Long> {

    default ArrivalTime findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> ValidationException.create(NOT_FOUND_BY_ID, id));
    }

}