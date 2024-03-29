package entity;

import base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Random;

@Entity
@Getter
@ToString
public class Account extends BaseEntity {

    private String number;
    @ToString.Exclude
    private Integer wrongPassword;
    @ManyToOne
    @ToString.Exclude
    private Customer customer;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private CreditCard creditCard;
    @OneToOne
    @Transient
    @ToString.Exclude
    private Transaction transaction;

    public Account() {
        this.number = "7008" + createRandomAccountNumber();
        this.wrongPassword = 0;
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

    public void setWrongPassword(Integer wrongPassword) {
        this.wrongPassword = wrongPassword;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
