package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee extends Person {

    private Integer code;
    @ManyToOne
    private BankBranch bankBranch;

}
