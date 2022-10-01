package repository;

import base.BaseRepository;
import entity.Employee;

public class EmployeeRepository implements BaseRepository<Employee> {
    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }
}
