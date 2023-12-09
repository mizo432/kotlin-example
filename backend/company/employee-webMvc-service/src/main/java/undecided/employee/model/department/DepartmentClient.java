package undecided.employee.model.department;

import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

public interface DepartmentClient {

    Mono<Department> findOneById(@PathVariable Long id);

}
