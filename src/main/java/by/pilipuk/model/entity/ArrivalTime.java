package by.pilipuk.model.entity;

import by.pilipuk.model.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@Entity(name = "arrival_times")
@Accessors(chain = true)
@FieldNameConstants
public class ArrivalTime extends BaseEntity {

    @Column(name = "check_in")
    private String checkIn;

    @Column(name = "check_out")
    private String checkOut;
}