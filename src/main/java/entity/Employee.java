package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="employee_type",
        discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class Employee extends Person {

    private Integer code;
    @ManyToOne(cascade = CascadeType.ALL)
    private BankBranch bankBranch;
    /*@Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;*/
    @ManyToOne
    private BankBoss bankBoss;

}
