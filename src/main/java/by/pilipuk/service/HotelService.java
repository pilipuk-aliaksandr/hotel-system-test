package by.pilipuk.service;

import by.pilipuk.dto.*;
import by.pilipuk.exception.ApplicationException;
import by.pilipuk.mapper.AmenityMapper;
import by.pilipuk.model.dto.GroupCountProjection;
import by.pilipuk.model.entity.Amenity;
import by.pilipuk.model.entity.Hotel;
import by.pilipuk.mapper.HotelMapper;
import by.pilipuk.mapper.HotelSpecificationMapper;
import by.pilipuk.repository.AmenityRepository;
import by.pilipuk.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static by.pilipuk.exception.ApplicationExceptionCode.INVALID_HISTOGRAM_PARAM;

@Service
@RequiredArgsConstructor
public class HotelService {

    private static final Set<String> VALID_PARAMS = Set.of("brand", "city", "country", "amenities");

    private final HotelMapper hotelMapper;
    private final HotelRepository hotelRepository;
    private final AmenityMapper amenityMapper;
    private final AmenityRepository amenityRepository;
    private final HotelSpecificationMapper hotelSpecificationMapper;

    @Transactional
    public HotelBriefDto createHotel(HotelWriteDto hotelWriteDto) {
        var hotel = hotelMapper.toEntity(hotelWriteDto);
        var savedHotel = hotelRepository.save(hotel);

        return hotelMapper.toBriefDto(savedHotel);
    }

    @Transactional(readOnly = true)
    public List<HotelBriefDto> getAllHotels() {

        return hotelRepository.findAll()
                .stream().map(hotelMapper::toBriefDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<HotelBriefDto> searchHotels(
            @Nullable String name,
            @Nullable String brand,
            @Nullable String city,
            @Nullable String country,
            @Nullable List<String> amenities
    ) {
        Specification<Hotel> spec = hotelSpecificationMapper.hotelSearchSpecification(name, brand, city, country, amenities);

        return hotelRepository.findAll(spec)
                .stream()
                .map(hotelMapper::toBriefDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public HotelFullDto getHotelById(Long hotelId) {
        var hotel = hotelRepository.findByIdOrThrow(hotelId);

        return hotelMapper.toFullDto(hotel);
    }

    @Transactional
    public void addAmenitiesToHotel(Long id, List<String> amenities) {
        var hotel = hotelRepository.findByIdOrThrow(id);

        Set<String> existingNames = hotel.getAmenities().stream()
                .map(Amenity::getName)
                .collect(Collectors.toSet());

        List<Amenity> newAmenities = amenities.stream()
                .filter(name -> !existingNames.contains(name))
                .map(name -> amenityMapper.toEntity(name, hotel))
                .toList();

        amenityRepository.saveAll(newAmenities);
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getHistogramHotels(String param) {
        if (param == null || !VALID_PARAMS.contains(param.toLowerCase())) {
            throw ApplicationException.create(INVALID_HISTOGRAM_PARAM, param);
        }

        List<GroupCountProjection> result = switch (param.toLowerCase()) {
            case "brand"    -> hotelRepository.countGroupByBrand();
            case "city"     -> hotelRepository.countGroupByCity();
            case "country"  -> hotelRepository.countGroupByCountry();
            case "amenities"-> amenityRepository.countGroupByName();
            default         -> throw new IllegalStateException(param);
        };

        return result.stream()
                .filter(p -> p.getKey() != null)
                .collect(Collectors.toMap(
                        GroupCountProjection::getKey,
                        p -> p.getCount().intValue()
                ));
    }
}