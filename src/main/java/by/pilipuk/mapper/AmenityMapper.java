package by.pilipuk.mapper;

import by.pilipuk.model.entity.Amenity;
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
}
