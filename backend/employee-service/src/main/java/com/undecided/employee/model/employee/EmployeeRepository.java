package com.undecided.employee.model.employee;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmployeeRepository {
    Map<Long,Employee> map = new HashMap<>();
    public EmployeeRepository(){
        map.put(1L,Employee.create(1L,"name1",1L));
        map.put(2L,Employee.create(2L,"name2",1L));
        map.put(3L,Employee.create(3L,"name3",1L));

    }
    public List<Employee> findAll() {
        return Lists.newArrayList(map.values());
    }

    public Employee save(Employee employee) {
        map.put(employee.getEmployeeId(), employee);
        return employee;
    }

    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(map.get(id));


    }

    public void deleteById(Long id) {
        map.remove(id);
    }
}
