package service;

import base.BaseRepository;
import base.BaseService;
import entity.CreditCard;
import repository.CreditCardRepository;

import java.util.Optional;

public class CreditCardService implements BaseService<CreditCard> {
    @Override
    public BaseRepository<CreditCard> getRepository() {
        return new CreditCardRepository();
    }

    public Optional<CreditCard> loadByNumber(String number){
        return ((CreditCardRepository) getRepository()).loadByNumber(number);
    }

}
