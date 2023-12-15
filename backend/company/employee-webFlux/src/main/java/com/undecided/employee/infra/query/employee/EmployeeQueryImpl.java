package com.undecided.employee.infra.query.employee;

import com.undecided.employee.model.department.Department;
import com.undecided.employee.model.department.DepartmentClient;
import com.undecided.employee.model.employee.Employee;
import com.undecided.employee.model.employee.EmployeeRepository;
import com.undecided.employee.model.prefecture.PrefectureClient;
import com.undecided.employee.model.prefecture.PrefectureDto;
import com.undecided.employee.service.EmployeeQuery;
import com.undecided.employee.service.EmployeeWithDepartment;
import io.micrometer.observation.annotation.Observed;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    @Observed
    public Mono<EmployeeWithDepartment> findOneBy(Long id) {
        log.info("find One By id.");
        return employeeRepository.findById(id)
                .flatMap(employee -> {
                    Long departmentId = employee.getDepartmentId();
                    Mono<Department> departmentMono = departmentClient.findOneById(departmentId);
                    Mono<PrefectureDto> prefectureMono = prefectureClient.findByCode("12");
                    return Mono.zip(departmentMono, prefectureMono)
                            .map(tuple ->
                                    EmployeeWithDepartment.reconstruct(employee, tuple.getT1(), tuple.getT2()));
                });
    }

    @Override
    @Observed
    public Flux<EmployeeWithDepartment> findEmployeesWithDepartment() {
        log.info("find employees with department.");
        return employeeRepository.findAll().flatMap(this::apply);
    }

    private Publisher<? extends EmployeeWithDepartment> apply(Employee employee) {
        Long departmentId = employee.getDepartmentId();
        Mono<Department> departmentMono = departmentClient.findOneById(departmentId);
        Mono<PrefectureDto> prefectureMono = prefectureClient.findByCode("12");
        return Mono.zip(departmentMono, prefectureMono)
                .map(tuple -> EmployeeWithDepartment.reconstruct(employee, tuple.getT1(), tuple.getT2()));
    }

}
