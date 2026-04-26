package by.pilipuk.mapper;

import by.pilipuk.model.entity.Amenity;
import by.pilipuk.model.entity.Hotel;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AmenityMapper {
    protected List<String> map(List<Amenity> amenities) {
        if (CollectionUtils.isEmpty(amenities)) {
            return Collections.emptyList();
        }
        return amenities.stream()
                .map(Amenity::getName)
                .toList();
    }

    public Amenity toEntity(String name, Hotel hotel) {
        return new Amenity()
                .setName(name)
                .setHotel(hotel);
    }

    public List<Amenity> toEntities(List<String> names, Hotel hotel) {
        if (CollectionUtils.isEmpty(names)) {
            return Collections.emptyList();
        }
        return names.stream()
                .map(name -> toEntity(name, hotel))
                .toList();
    }
}