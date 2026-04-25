package by.pilipuk.mapper;

import by.pilipuk.model.entity.Amenity;
import org.mapstruct.Mapper;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AmenityMapper {
    protected List<String> map(List<Amenity> amenities) {
        if (amenities == null) {
            return Collections.emptyList();
        }
        return amenities.stream()
                .map(Amenity::getName)
                .toList();
    }
}
