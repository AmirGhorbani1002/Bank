package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Customer extends Person{

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;
    @ManyToMany
    private Set<BankBranch> bankBranch;

}
