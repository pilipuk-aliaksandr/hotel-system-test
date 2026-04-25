package by.pilipuk.environment.data;

import by.pilipuk.dto.ArrivalTimeDto;
import by.pilipuk.environment.data.dtoCreators.AddressDtoCreator;
import by.pilipuk.environment.data.dtoCreators.ArrivalTimeDtoCreator;
import by.pilipuk.environment.data.dtoCreators.ContactsDtoCreator;
import by.pilipuk.environment.data.dtoCreators.HotelDtoCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoCreators {

    public final HotelDtoCreator writeHotel;

    public final AddressDtoCreator addressWriteDto;

    public final ArrivalTimeDtoCreator arrivalTimeDto;

    public final ContactsDtoCreator contactsDto;

}