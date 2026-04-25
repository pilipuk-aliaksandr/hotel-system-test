package by.pilipuk.environment.data.entityCreators;

import by.pilipuk.model.entity.*;
import by.pilipuk.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotelCreator {

    private final HotelRepository hotelRepository;

    public Hotel createHotel(Address address, Contact contact, ArrivalTime arrivalTime) {

        Hotel hotel = new Hotel()
                .setName("DoubleTree by Hilton Minsk")
                .setDescription("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
                .setBrand("Hilton")
                .setAddress(address)
                .setContacts(contact)
                .setArrivalTime(arrivalTime);

        return hotelRepository.save(hotel);
    }
}
