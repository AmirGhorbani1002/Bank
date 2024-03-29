package entity;

import base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class BankBranch extends BaseEntity {

    private String name;
    private Integer code;
    @ManyToMany(mappedBy = "bankBranch",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Customer> customers = new HashSet<>();
    @OneToMany(mappedBy = "bankBranch",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Employee> employees = new HashSet<>();

}
