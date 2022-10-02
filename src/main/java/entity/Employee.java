package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee extends Person {

    private Integer code;
    @ManyToOne(cascade = CascadeType.ALL)
    private BankBranch bankBranch;

}
