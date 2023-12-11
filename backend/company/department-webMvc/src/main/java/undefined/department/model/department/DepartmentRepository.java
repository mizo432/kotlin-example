package undefined.department.model.department;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Observed
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
