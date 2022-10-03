package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee extends Person {

    private Integer code;
    @ManyToOne(cascade = CascadeType.ALL)
    private BankBranch bankBranch;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

}
