package entity;

import base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Transaction extends BaseEntity {

    @OneToOne
    private Account account1;
    @OneToOne
    private Account account2;
    private Double amount;

}
