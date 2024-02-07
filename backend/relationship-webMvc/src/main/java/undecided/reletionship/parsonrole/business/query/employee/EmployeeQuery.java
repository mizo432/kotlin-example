package undecided.reletionship.parsonrole.business.query.employee;

import java.util.List;

public interface EmployeeQuery {
    List<EmployeeDto> findAll();

    EmployeeDto findByEmployeeNo(String employeeNo);
}
