package by.pilipuk.repository;

import by.pilipuk.model.entity.Address;
import by.pilipuk.exeption.ApplicationException;
import org.springframework.data.jpa.repository.JpaRepository;
import static by.pilipuk.exeption.ApplicationExceptionCode.NOT_FOUND_BY_ID;

public interface AddressRepository extends JpaRepository<Address, Long> {

    default Address findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> ApplicationException.create(NOT_FOUND_BY_ID, id));
    }
}