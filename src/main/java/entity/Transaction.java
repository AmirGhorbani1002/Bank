package entity;

import base.BaseEntity;
import exception.NegativeException;
import exception.NotEnoughException;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transaction extends BaseEntity {

    @OneToOne
    private Account originAccount;
    @OneToOne
    private Account destinationAccount;
    private Double amount;

    public void deposit() {
        checkAmount();
        destinationAccount
                .getCreditCard()
                .setAmount(this.amount + destinationAccount
                        .getCreditCard()
                        .getAmount());
    }

    public void withdraw() {
        checkAmount();
        destinationAccount
                .getCreditCard()
                .setAmount(destinationAccount
                        .getCreditCard()
                        .getAmount() - this.amount);
    }

    private void checkAmount() {
        if (this.amount < 0) {
            throw new NegativeException();
        } else if (this.amount > originAccount
                .getCreditCard()
                .getAmount()) {
            throw new NotEnoughException();
        }
    }

}
