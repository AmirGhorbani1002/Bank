package service;

import base.BaseRepository;
import base.BaseService;
import entity.BankBranch;
import repository.BankBranchRepository;

public class BankBranchService implements BaseService<BankBranch> {
    @Override
    public BaseRepository<BankBranch> getRepository() {
        return new BankBranchRepository();
    }
}
