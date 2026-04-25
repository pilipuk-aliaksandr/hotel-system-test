package by.pilipuk.environment.data.entityCreators;

import by.pilipuk.model.entity.Contact;
import by.pilipuk.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactCreator {

    private final ContactRepository contactRepository;

    public Contact createContact() {

        return new Contact()
                .setPhone("+375 17 309-80-00")
                .setEmail("doubletreeminsk.info@hilton.com");
    }
}
