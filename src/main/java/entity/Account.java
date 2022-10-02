package entity;

import base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Entity
@Getter
@Setter
public class Account extends BaseEntity {

    private String number;
    private Boolean blocked;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private CreditCard creditCard;
    @OneToOne
    @Transient
    private Transaction transaction;

    public Account() {
        this.number = "7008" + createRandomAccountNumber();
        this.blocked = false;
        this.creditCard = new CreditCard();
        this.creditCard.setAccount(this);
    }

    private String createRandomAccountNumber(){
        Random random = new Random();
        return random.ints(48,58)
                .limit(8)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
