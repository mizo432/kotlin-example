package undefined.department.model.department;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "departments", schema = "department")
public class Department {

    @Id
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "name")
    private String name;

}
