package repository;

import base.BaseRepository;
import entity.Transaction;

public class TransactionRepository implements BaseRepository<Transaction> {
    @Override
    public Class<Transaction> getEntityClass() {
        return Transaction.class;
    }
}
