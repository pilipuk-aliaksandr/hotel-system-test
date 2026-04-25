package by.pilipuk.environment.data;

import by.pilipuk.environment.data.entityCreators.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityCreators {

    public final AddressCreator addressCreator;

    public final ContactCreator contactCreator;

    public final ArrivalTimeCreator arrivalTimeCreator;

    public final HotelCreator hotelCreator;
}