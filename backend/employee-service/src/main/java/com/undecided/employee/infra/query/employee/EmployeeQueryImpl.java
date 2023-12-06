package com.undecided.employee.infra.query.employee;

import com.undecided.employee.model.depertment.Department;
import com.undecided.employee.model.depertment.DepartmentClient;
import com.undecided.employee.model.employee.Employee;
import com.undecided.employee.model.employee.EmployeeRepository;
import com.undecided.employee.model.prefecture.PrefectureClient;
import com.undecided.employee.model.prefecture.PrefectureDto;
import com.undecided.employee.service.EmployeeQuery;
import com.undecided.employee.service.EmployeeWithDepartment;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class EmployeeQueryImpl implements EmployeeQuery {
    private final EmployeeRepository employeeRepository;
    private final DepartmentClient departmentClient;
    private final PrefectureClient prefectureClient;

    @Override
    public Mono<EmployeeWithDepartment> findOneBy(Long id) {
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
    public Flux<EmployeeWithDepartment> getEmployeesWithDepartment() {
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
