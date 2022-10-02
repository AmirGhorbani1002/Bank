package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends Person{

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<BankBranch> bankBranch = new HashSet<>();


    public Customer(String firstname, String lastname, String nationalCode, String password) {
        super(firstname, lastname, nationalCode, password);
    }
}
