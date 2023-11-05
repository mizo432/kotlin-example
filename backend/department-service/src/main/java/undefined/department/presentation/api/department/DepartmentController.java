package undefined.department.presentation.api.department;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import undefined.common.EntityNotFoundException;
import undefined.department.model.department.Department;
import undefined.department.model.department.DepartmentRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    List<Department> findAll() {
        return departmentRepository.findAll();

    }

    @PostMapping("/departments")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    Department newEmployee(@RequestBody Department newEmployee) {
        return departmentRepository.save(newEmployee);
    }

    // Single item

    @GetMapping("/departments/{id}")
    Department one(@PathVariable Long id) {

        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("department", id));
    }

    @PutMapping("/departments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    Department replaceEmployee(@RequestBody Department newDepartment, @PathVariable Long id) {

        return departmentRepository.findById(id)
                .map(employee -> {
                    return departmentRepository.save(newDepartment);
                })
                .orElseGet(() -> {
                    return departmentRepository.save(newDepartment);
                });
    }

    @DeleteMapping("/departments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEmployee(@PathVariable Long id) {
        departmentRepository.deleteById(id);
    }
}
