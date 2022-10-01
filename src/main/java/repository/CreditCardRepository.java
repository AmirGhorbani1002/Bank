package repository;

import base.BaseRepository;
import entity.CreditCard;

public class CreditCardRepository implements BaseRepository<CreditCard> {
    @Override
    public Class<CreditCard> getEntityClass() {
        return CreditCard.class;
    }
}
