package undecided.reletionship.business.query.personrole.employee;

import java.util.List;

public interface EmployeeQuery {
    List<EmployeeDto> findAll();

    EmployeeDto findOneByEmployeeNo(String employeeNo);
}
