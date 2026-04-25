package by.pilipuk.controller;

import by.pilipuk.api.HotelApi;
import by.pilipuk.dto.HotelBriefDto;
import by.pilipuk.dto.HotelFullDto;
import by.pilipuk.dto.HotelWriteDto;
import by.pilipuk.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HotelController implements HotelApi {

    private final HotelService hotelService;

    @Override
    public HotelBriefDto createHotel(HotelWriteDto hotelWriteDto) {
        return hotelService.createHotel(hotelWriteDto);
    }

    @Override
    public List<HotelBriefDto> getHotels() {
        return hotelService.getAllHotels();
    }

    @Override
    public List<HotelBriefDto> searchHotels(@Nullable String name, @Nullable String brand, @Nullable String city, @Nullable String country, @Nullable List<String> amenities) {
        return hotelService.searchHotels(name, brand, city, country, amenities);
    }

    @Override
    public HotelFullDto getHotelById(Long id) {
        return hotelService.getHotelById(id);
    }

    @Override
    public void addAmenitiesToHotel(Long id, List<String> requestBody) {
        hotelService.addAmenitiesToHotel(id, requestBody);
    }

    @Override
    public Map<String, Integer> getHistogramHotels(String param) {
        return hotelService.getHistogramHotels(param);
    }
}