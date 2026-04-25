package by.pilipuk.repository;

import by.pilipuk.entity.Address;
import by.pilipuk.exeption.ValidationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static by.pilipuk.model.enums.ValidationCode.NOT_FOUND_BY_ID;

public interface AddressRepository extends JpaRepository<Address, Long> {

    default Address findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> ValidationException.create(NOT_FOUND_BY_ID, id));
    }
}