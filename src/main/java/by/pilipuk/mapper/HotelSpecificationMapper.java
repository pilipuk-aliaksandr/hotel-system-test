package by.pilipuk.mapper;

import by.pilipuk.model.entity.Address;
import by.pilipuk.model.entity.Amenity;
import by.pilipuk.model.entity.Hotel;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.List;

@Component
public class HotelSpecificationMapper {

    public Specification<Hotel> hotelSearchSpecification(
            @Nullable String name,
            @Nullable String brand,
            @Nullable String city,
            @Nullable String country,
            @Nullable List<String> amenities
    ) {
        return Specification.allOf(
                hasName(name),
                hasBrand(brand),
                hasCity(city),
                hasCountry(country),
                hasAmenities(amenities)
        );
    }

    private static Specification<Hotel> hasName(String name) {
        return (root, _, cb) ->
                (name == null || name.isBlank())
                        ? cb.conjunction()
                        : cb.like(cb.lower(root.get(Hotel.Fields.name)), "%" + name.toLowerCase() + "%");
    }

    private static Specification<Hotel> hasBrand(String brand) {
        return (root, _, cb) ->
                (brand == null || brand.isBlank())
                        ? cb.conjunction()
                        : cb.equal(cb.lower(root.get(Hotel.Fields.brand)), brand.toLowerCase());
    }

    private static Specification<Hotel> hasCity(String city) {
        return (root, _, cb) ->
                (city == null || city.isBlank())
                        ? cb.conjunction()
                        : cb.equal(cb.lower(root.get(Hotel.Fields.address).get(Address.Fields.city)), city.toLowerCase());
    }

    private static Specification<Hotel> hasCountry(String country) {
        return (root, _, cb) ->
                (country == null || country.isBlank())
                        ? cb.conjunction()
                        : cb.equal(cb.lower(root.get(Hotel.Fields.address).get(Address.Fields.country)), country.toLowerCase());
    }

    private static Specification<Hotel> hasAmenities(List<String> amenities) {
        return (root, _, cb) -> {
            if (CollectionUtils.isEmpty(amenities)) {
                return cb.conjunction();
            }
            Join<Hotel, Amenity> amenityJoin = root.join(Hotel.Fields.amenities);
            return amenityJoin.get(Amenity.Fields.name).in(amenities);
        };
    }
}