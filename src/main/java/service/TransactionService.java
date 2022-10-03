package service;

import base.BaseRepository;
import base.BaseService;
import entity.Transaction;
import repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

public class TransactionService implements BaseService<Transaction> {
    @Override
    public BaseRepository<Transaction> getRepository() {
        return new TransactionRepository();
    }

    public Optional<List<Transaction>> loadAllByDate(Long number) {
        return ((TransactionRepository) getRepository()).loadAllByDate(number);
    }

}
