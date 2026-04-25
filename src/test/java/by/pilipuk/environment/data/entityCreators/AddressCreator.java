package by.pilipuk.environment.data.entityCreators;

import by.pilipuk.model.entity.Address;
import by.pilipuk.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressCreator {

    private final AddressRepository addressRepository;

    public Address createAddress() {

        Address address = new Address()
                .setHouseNumber(9)
                .setStreet("Pobediteley Avenue")
                .setCity("Minsk")
                .setCountry("Belarus")
                .setPostCode("220004");

        return address;
    }
}
