package undecided.account.service;

import java.util.List;

public interface EmployeeQuery {
    EmployeeWithDepartment findOneBy(Long id);

    List<EmployeeWithDepartment> findEmployeesWithDepartment();
}
