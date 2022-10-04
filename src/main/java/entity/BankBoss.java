package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class BankBoss extends Person{

    @OneToMany(mappedBy = "bankBoss",cascade = CascadeType.ALL)
    private final Set<Employee> employees = new HashSet<>();

}
