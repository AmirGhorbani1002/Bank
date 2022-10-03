package entity;

import base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Random;

@Entity
@Getter
@ToString
public class CreditCard extends BaseEntity {

    private String number;
    private Double amount;
    private Integer cvv2;
    private LocalDate expired;
    private String password;
    @OneToOne
    @MapsId
    @ToString.Exclude
    private Account account;

    public CreditCard() {
        this.number = "50470610" + createRandomCardNumber();
        this.amount = 0D;
        this.cvv2 = createRandomCvv2();
        this.expired = createExpiredDate();
    }
    public CreditCard(Double amount) {
        this.number = "50470610" + createRandomCardNumber();
        this.amount = amount;
        this.cvv2 = createRandomCvv2();
        this.expired = createExpiredDate();
    }

    private String createRandomCardNumber(){
        Random random = new Random();
        return random.ints(48,58)
                .limit(8)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private Integer createRandomCvv2(){
        Random random = new Random();
        return random.nextInt(1000,10000);
    }

    private LocalDate createExpiredDate(){
        return LocalDate.now().plusYears(5);
    }

    public void setExpired(LocalDate expired) {
        this.expired = expired;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
