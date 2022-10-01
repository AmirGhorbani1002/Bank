package entity;

import base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class BankBranch extends BaseEntity {

    private String name;
    private Integer code;
    @ManyToMany(mappedBy = "bankBranch",cascade = CascadeType.ALL)
    private Set<Customer> customers ;
    @OneToMany(mappedBy = "bankBranch",cascade = CascadeType.ALL)
    private Set<Employee> employees ;

}
