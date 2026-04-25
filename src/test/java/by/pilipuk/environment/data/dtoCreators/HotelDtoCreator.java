package by.pilipuk.environment.data.dtoCreators;

import by.pilipuk.dto.AddressDto;
import by.pilipuk.dto.ArrivalTimeDto;
import by.pilipuk.dto.ContactsDto;
import by.pilipuk.dto.HotelWriteDto;
import org.springframework.stereotype.Component;

@Component
public class HotelDtoCreator {

    public HotelWriteDto createHotelWriteDto(AddressDto addressDto, ContactsDto contactsDto, ArrivalTimeDto arrivalTimeDto) {
        return new HotelWriteDto()
                .name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
                .brand("Hilton")
                .address(addressDto)
                .contacts(contactsDto)
                .arrivalTime(arrivalTimeDto);
    }
}