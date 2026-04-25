package by.pilipuk.environment.data.dtoCreators;

import by.pilipuk.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDtoCreator {

    public AddressDto createAddressDto() {
        return new AddressDto()
                .houseNumber(9)
                .street("Pobediteley Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220004");
    }

}