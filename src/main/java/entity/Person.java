package entity;

import base.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Person extends BaseEntity {

    private String firstname;
    private String lastname;
    private String nationalCode;

}
