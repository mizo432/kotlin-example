package undecided.employee.infra.client.department;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Mono;
import undecided.employee.model.department.Department;
import undecided.employee.model.department.DepartmentClient;

@Component
public class DepartmentClientImpl implements DepartmentClient {
    private final RestClient restClient;

    public DepartmentClientImpl(@Qualifier("departmentRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Mono<Department> findOneById(Long id) {
        Department department = restClient.get()
                .uri("/api/v1/departments/{id}", id)
                .retrieve()
                .body(Department.class);

        if (department == null) {
            return Mono.empty();
        }

        return Mono.just(department);
    }
}
