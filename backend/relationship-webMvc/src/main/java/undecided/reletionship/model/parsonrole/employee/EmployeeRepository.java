package undecided.reletionship.model.parsonrole.employee;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Observed
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeNo(String employeeNo);

    void deleteByEmployeeNo(String employeeNo);

}
