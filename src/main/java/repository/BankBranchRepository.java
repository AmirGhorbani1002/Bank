package repository;

import base.BaseRepository;
import entity.BankBranch;

public class BankBranchRepository implements BaseRepository<BankBranch> {
    @Override
    public Class<BankBranch> getEntityClass() {
        return BankBranch.class;
    }
}
