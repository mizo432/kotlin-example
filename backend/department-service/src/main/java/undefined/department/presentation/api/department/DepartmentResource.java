package undefined.department.presentation.api.department;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import undefined.common.EntityNotFoundException;
import undefined.department.model.department.Department;
import undefined.department.model.department.DepartmentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentResource {

    private static final Logger log = LoggerFactory.getLogger(DepartmentResource.class);

    private final DepartmentRepository departmentRepository;

    public DepartmentResource(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    List<Department> findAll() {
        log.info("get all departments.");
        return departmentRepository.findAll();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    Department newEmployee(@RequestBody Department newEmployee) {
        return departmentRepository.save(newEmployee);
    }

    // Single item

    @GetMapping(path = "/{id}")
    Department getDepartmentById(@PathVariable Long id) {
        log.info("get department by Id.");

        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("department", id));
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    Department putDepartment(@RequestBody Department newDepartment, @PathVariable Long id) {
        log.info("put department.");

        return departmentRepository.findById(id)
                .map(employee -> {
                    return departmentRepository.save(newDepartment);
                })
                .orElseGet(() -> {
                    return departmentRepository.save(newDepartment);
                });
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEmployee(@PathVariable Long id) {
        log.info("get department by Id.");
        departmentRepository.deleteById(id);
    }
}
