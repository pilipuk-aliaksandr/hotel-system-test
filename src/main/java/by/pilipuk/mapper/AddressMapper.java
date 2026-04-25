package by.pilipuk.mapper;

import by.pilipuk.dto.AddressDto;
import by.pilipuk.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {
    @Named("addressToString")
    protected String addressToString(Address address) {
        if (address == null) return null;
        return String.format("%s %s, %s, %s, %s",
                address.getHouseNumber(), address.getStreet(),
                address.getCity(), address.getPostCode(), address.getCountry());
    }

    @Named("addressDtoToString")
    protected String addressDtoToString(AddressDto addressDto) {
        if (addressDto == null) return null;
        return String.format("%s %s, %s, %s, %s",
                addressDto.getHouseNumber(), addressDto.getStreet(),
                addressDto.getCity(), addressDto.getPostCode(), addressDto.getCountry());
    }
}
