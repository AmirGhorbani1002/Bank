package repository;

import base.BaseRepository;
import entity.Account;

public class AccountRepository implements BaseRepository<Account> {
    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }
}
