package by.pilipuk.service;

import by.pilipuk.dto.*;
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
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelMapper hotelMapper;
    private final HotelRepository hotelRepository;
    private final AmenityRepository amenityRepository;
    private final HotelSpecificationMapper hotelSpecificationMapper;

    @Transactional
    public HotelBriefDto createHotel(HotelWriteDto hotelWriteDto) {
        var hotel = hotelMapper.toEntity(hotelWriteDto);
        var savedHotel = hotelRepository.save(hotel);

        return hotelMapper.toBriefDto(savedHotel);
    }

    public List<HotelBriefDto> getAllHotels() {

        return hotelRepository.findAll()
                .stream().map(hotelMapper::toBriefDto)
                .toList();
    }

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

    public HotelFullDto getHotelById(Long hotelId) {
        var hotel = hotelRepository.findByIdOrThrow(hotelId);

        return hotelMapper.toFullDto(hotel);
    }

    public void addAmenitiesToHotel(Long id, List<String> amenities) {
        var hotel = hotelRepository.findByIdOrThrow(id);

        List<Amenity> filledAmenities = amenities.stream()
                .map(name -> new Amenity().setName(name).setHotel(hotel))
                .toList();

        amenityRepository.saveAll(filledAmenities);
    }

    public Map<String, Integer> getHistogramHotels(String param) {

        return hotelRepository.findAll().stream()
                .flatMap(h -> switch (param.toLowerCase()) {
                    case "brand" -> Stream.of(h.getBrand());
                    case "city" -> Stream.of(h.getAddress().getCity());
                    case "country" -> Stream.of(h.getAddress().getCountry());
                    case "amenities" -> h.getAmenities().stream().map(Amenity::getName);
                    default -> throw new IllegalArgumentException("Unknown param: " + param);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(val -> val, Collectors.summingInt(e -> 1)));
    }
}