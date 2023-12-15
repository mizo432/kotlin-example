package undecided.employee.model.department;

import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface DepartmentClient {

    @GetExchange("/api/v1/departments/{id}")
    @Observed
    Department findOneById(@PathVariable Long id);

}
