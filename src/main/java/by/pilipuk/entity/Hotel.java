package by.pilipuk.entity;

import by.pilipuk.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "hotels")
@Accessors(chain = true)
@FieldNameConstants
public class Hotel extends BaseEntity {

    private String name;

    private String brand;

    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contacts;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_time_id", nullable = false)
    private ArrivalTime arrivalTime;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Amenity> amenities;

}