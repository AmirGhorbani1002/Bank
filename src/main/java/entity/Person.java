package entity;

import base.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class Person extends BaseEntity {

    private String firstname;
    private String lastname;
    private String nationalCode;
    private String password;

    public Person(String firstname, String lastname, String nationalCode, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalCode = nationalCode;
        this.password = password;
    }
}
