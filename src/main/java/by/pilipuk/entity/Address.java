package by.pilipuk.entity;

import by.pilipuk.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@Entity(name = "addresses")
@Accessors(chain = true)
@FieldNameConstants
public class Address extends BaseEntity {

    @Column(name = "house_number")
    private String houseNumber;

    private String street;

    private String city;

    private String country;

    @Column(name = "post_code")
    private String postCode;
}