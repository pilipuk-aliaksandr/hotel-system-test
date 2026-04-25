package by.pilipuk.environment.service;

import by.pilipuk.dto.HotelBriefDto;
import by.pilipuk.dto.HotelFullDto;
import by.pilipuk.dto.HotelWriteDto;
import by.pilipuk.model.entity.Hotel;
import by.pilipuk.environment.data.DtoCreators;
import by.pilipuk.environment.data.EntityCreators;
import by.pilipuk.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class HotelCreationTestService {

    private final HotelMapper hotelMapper;
    private final EntityCreators entityCreators;
    private final DtoCreators dtoCreators;

    @Transactional
    public Hotel createHotel() {

        return entityCreators.hotelCreator.createHotel(
                entityCreators.addressCreator.createAddress(),
                entityCreators.contactCreator.createContact(),
                entityCreators.arrivalTimeCreator.createArrivalTime()
        );
    }

    @Transactional
    public HotelBriefDto createHotelBriefDto() {

        return hotelMapper.toBriefDto(createHotel());
    }

    @Transactional
    public HotelFullDto createHotelFullDto() {

        return hotelMapper.toFullDto(createHotel());
    }

    @Transactional
    public HotelWriteDto createHotelWriteDto() {

        return dtoCreators.writeHotel.createHotelWriteDto(
                dtoCreators.addressWriteDto.createAddressDto(),
                dtoCreators.contactsDto.createContactsDto(),
                dtoCreators.arrivalTimeDto.createArrivalTimeDto()
        );
    }
}