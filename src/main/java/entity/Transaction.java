package entity;

import base.BaseEntity;
import exception.NegativeException;
import exception.NotEnoughException;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends BaseEntity {

    @OneToOne
    private Account originAccount;
    @OneToOne
    private Account destinationAccount;
    private Double amount;

    public Transaction(Account originAccount, Account destinationAccount, Double amount) {
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public void selfDeposit() throws NegativeException {
        if (this.amount < 0) {
            throw new NegativeException();
        }
        originAccount
                .getCreditCard()
                .setAmount((this.amount - 600) + originAccount
                        .getCreditCard()
                        .getAmount());
    }

    public void deposit() throws NegativeException {
        checkAmount();
        destinationAccount
                .getCreditCard()
                .setAmount(this.amount + destinationAccount
                        .getCreditCard()
                        .getAmount());
        originAccount
                .getCreditCard()
                .setAmount(originAccount
                        .getCreditCard()
                        .getAmount() - this.amount - 600);
    }

    private void checkAmount() throws NegativeException {
        if (this.amount < 0) {
            throw new NegativeException();
        } else if (this.amount + 600 > originAccount
                .getCreditCard()
                .getAmount()) {
            throw new NotEnoughException();
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "originAccount=" + originAccount.getNumber() +
                ", destinationAccount=" + destinationAccount.getNumber() +
                ", amount=" + amount +
                '}';
    }
}
