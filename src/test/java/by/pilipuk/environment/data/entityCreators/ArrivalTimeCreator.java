package by.pilipuk.environment.data.entityCreators;

import by.pilipuk.model.entity.ArrivalTime;
import by.pilipuk.repository.ArrivalTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArrivalTimeCreator {

    private final ArrivalTimeRepository arrivalTimeRepository;

    public ArrivalTime createArrivalTime() {

        return new ArrivalTime()
                .setCheckIn("14:00")
                .setCheckOut("12:00");
    }
}
