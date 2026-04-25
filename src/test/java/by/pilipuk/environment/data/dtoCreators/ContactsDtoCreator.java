package by.pilipuk.environment.data.dtoCreators;

import by.pilipuk.dto.ContactsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsDtoCreator {

    public ContactsDto createContactsDto() {
        return new ContactsDto()
                .phone("+375 17 309-80-00")
                .email("doubletreeminsk.info@hilton.com");
    }
}