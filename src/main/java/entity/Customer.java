package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Customer extends Person{

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;
    @ManyToMany
    private Set<BankBranch> bankBranch;

}
