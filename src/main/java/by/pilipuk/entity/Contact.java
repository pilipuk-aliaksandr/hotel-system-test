package by.pilipuk.entity;

import by.pilipuk.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@Entity(name = "contacts")
@Accessors(chain = true)
@FieldNameConstants
public class Contact extends BaseEntity {

    private String phone;

    private String email;
}
