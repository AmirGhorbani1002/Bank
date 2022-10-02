package service;

import base.BaseRepository;
import base.BaseService;
import entity.Account;
import repository.AccountRepository;

public class AccountService implements BaseService<Account> {

    @Override
    public BaseRepository<Account> getRepository() {
        return new AccountRepository();
    }
}
