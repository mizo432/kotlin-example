package undecided.account.infra.query.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import undecided.account.model.department.Department;
import undecided.account.model.employee.Employee;
import undecided.account.model.employee.EmployeeRepository;
import undecided.account.model.department.DepartmentClient;
import undecided.account.model.prefecture.PrefectureClient;
import undecided.account.model.prefecture.PrefectureDto;
import undecided.account.service.EmployeeQuery;
import undecided.account.service.EmployeeWithDepartment;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeQueryImpl implements EmployeeQuery {
    private final static Logger log = LoggerFactory.getLogger(EmployeeQueryImpl.class);
    private final EmployeeRepository employeeRepository;
    private final DepartmentClient departmentClient;
    private final PrefectureClient prefectureClient;

    public EmployeeQueryImpl(EmployeeRepository employeeRepository, DepartmentClient departmentClient, PrefectureClient prefectureClient) {
        this.employeeRepository = employeeRepository;
        this.departmentClient = departmentClient;
        this.prefectureClient = prefectureClient;
    }

    @Override
    public EmployeeWithDepartment findOneBy(Long id) {
        log.info("find One By id.");
        Employee employee = employeeRepository.findById(id);
        Long departmentId = employee.getDepartmentId();
        Department department = departmentClient.findOneById(departmentId);
        PrefectureDto prefecture = prefectureClient.findByCode("12");
        return EmployeeWithDepartment.reconstruct(employee, department, prefecture);

    }

    @Override
    public List<EmployeeWithDepartment> findEmployeesWithDepartment() {
        log.info("find employees with department.");
        return employeeRepository.findAll().stream().map(employee -> {
            Long departmentId = employee.getDepartmentId();
            Department department = departmentClient.findOneById(departmentId);
            PrefectureDto prefecture = prefectureClient.findByCode("12");
            return EmployeeWithDepartment.reconstruct(employee, department, prefecture);

        }).collect(Collectors.toList());

    }


}
