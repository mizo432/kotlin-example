package undecided.employee.model.employee;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EmployeeRepository {
    Map<Long, Employee> map = new HashMap<>();

    public EmployeeRepository() {
        map.put(1L, Employee.create(1L, "name1", 1L));
        map.put(2L, Employee.create(2L, "name2", 1L));
        map.put(3L, Employee.create(3L, "name3", 1L));

    }

    public List<Employee> findAll() {
        return new ArrayList<Employee>(map.values());

    }

    public Employee save(Employee employee) {
        map.put(employee.getEmployeeId(), employee);
        return employee;
    }

    public Employee findById(Long id) {
        return map.get(id);
    }

    public void deleteById(Long id) {
        map.remove(id);
    }
}
