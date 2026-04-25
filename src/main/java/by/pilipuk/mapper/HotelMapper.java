package by.pilipuk.mapper;

import by.pilipuk.dto.*;
import by.pilipuk.entity.Address;
import by.pilipuk.entity.Amenity;
import by.pilipuk.entity.Hotel;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class HotelMapper {

    public abstract HotelFullDto toFullDto(Hotel hotel);

    protected List<String> map(List<Amenity> amenities) {
        return amenities.stream()
                .map(Amenity::getName)
                .toList();
    }

    @Mapping(target = "address", source = "address", qualifiedByName = "addressToString")
    @Mapping(target = "phone", source = "contacts.phone")
    public abstract HotelBriefDto toBriefDto(Hotel hotel);

    @Named("addressToString")
    protected String addressToString(Address address) {
        if (address == null) return null;
        return String.format("%s %s, %s, %s, %s",
                address.getHouseNumber(), address.getStreet(),
                address.getCity(), address.getPostCode(), address.getCountry());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract Hotel toEntity(HotelWriteDto hotelWriteDto);
}