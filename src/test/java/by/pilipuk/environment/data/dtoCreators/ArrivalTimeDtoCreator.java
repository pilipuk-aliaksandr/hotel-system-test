package by.pilipuk.environment.data.dtoCreators;

import by.pilipuk.dto.ArrivalTimeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArrivalTimeDtoCreator {

    public ArrivalTimeDto createArrivalTimeDto() {
        return new ArrivalTimeDto()
                .checkIn("14:00")
                .checkOut("12:00");
    }
}