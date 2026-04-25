package by.pilipuk.controller;

import by.pilipuk.environment.service.DBTruncateTestService;
import by.pilipuk.environment.service.HotelCreationTestService;
import by.pilipuk.mapper.HotelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@DisplayName("Test all methods from HotelController.class")
class HotelControllerTest extends BaseControllerTest {

    @Autowired
    private DBTruncateTestService dbTestService;

    @Autowired
    private HotelCreationTestService creationHotelTestService;

    @Autowired
    private HotelMapper hotelMapper;

    @BeforeEach
    void setUp() {
        dbTestService.truncateAllTables();
    }

    @Test
    void createHotel() {
        //given

        //when

        //then
        performPostRequest("/hotels", creationHotelTestService.createHotelWriteDto());
    }

    @Test
    void getHotels() {
        //given
        var expectedOrderDto = Collections.singletonList(creationHotelTestService.createHotelBriefDto());

        //when

        //then
        performGetRequest("/hotels", expectedOrderDto);
    }

    @Test
    void searchHotels() {
        //given
        var hotelFullDto = creationHotelTestService.createHotelFullDto();

        var expectedBriefDto = hotelMapper.toBriefDto(hotelFullDto);
        var expectedBriefDtoList = Collections.singletonList(expectedBriefDto);

        //when
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", hotelFullDto.getName());
        params.add("brand", hotelFullDto.getBrand());
        params.add("city", hotelFullDto.getAddress().getCity());
        params.add("country", hotelFullDto.getAddress().getCountry());
        if (hotelFullDto.getAmenities() != null) {
            params.addAll("amenities", hotelFullDto.getAmenities());
        }

        //then
        performGetRequestWithParams("/search", params, expectedBriefDtoList);
    }

    @Test
    void getHotelById() {
        performGetRequest("/hotels/{id}", 1, creationHotelTestService.createHotelFullDto());
    }

    @Test
    void addAmenitiesToHotel() {
        //given
        var hotelFullDto = creationHotelTestService.createHotelFullDto();
        Long hotelId = hotelFullDto.getId();

        //when
        List<String> amenitiesRequest = List.of(
                "Free parking",
                "Free WiFi",
                "Non-smoking rooms",
                "Concierge",
                "On-site restaurant",
                "Fitness center",
                "Pet-friendly rooms",
                "Room service",
                "Business center",
                "Meeting rooms"
        );

        //then
        performPostRequest("/hotels/" + hotelId + "/amenities", amenitiesRequest);
    }

    @Test
    void getHistogramHotels() {
        //given
        var hotel1 = creationHotelTestService.createHotelFullDto();

        //when
        Map<String, Integer> expected = Map.of(
                hotel1.getAddress().getCity(), 1);

        //then
        performGetHistogramRequest("/histogram/city", expected);
    }
}