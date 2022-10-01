package entity;

import base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account extends BaseEntity {

    private String number;
    private Boolean blocked;
    @ManyToOne
    private Customer customer;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private CreditCard creditCard;
    @OneToOne
    @Transient
    private Transaction transaction;

}
