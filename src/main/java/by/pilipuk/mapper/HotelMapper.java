package by.pilipuk.mapper;

import by.pilipuk.dto.HotelFullDto;
import by.pilipuk.dto.HotelBriefDto;
import by.pilipuk.dto.HotelWriteDto;
import by.pilipuk.model.entity.Hotel;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {AddressMapper.class, AmenityMapper.class}
)
@Setter(onMethod_ = @Autowired)
public abstract class HotelMapper {

    public abstract HotelFullDto toFullDto(Hotel hotel);

    @Mapping(target = "address", source = "address", qualifiedByName = "addressToString")
    @Mapping(target = "phone", source = "contacts.phone")
    public abstract HotelBriefDto toBriefDto(Hotel hotel);

    @Mapping(target = "address", source = "address", qualifiedByName = "addressDtoToString")
    @Mapping(target = "phone", source = "contacts.phone")
    public abstract HotelBriefDto toBriefDto(HotelFullDto hotelFullDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract Hotel toEntity(HotelWriteDto hotelWriteDto);
}