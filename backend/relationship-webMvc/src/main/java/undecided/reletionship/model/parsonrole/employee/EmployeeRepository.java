package undecided.reletionship.model.parsonrole.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeNo(String employeeNo);

    void deleteByEmployeeNo(String employeeNo);
    
}
