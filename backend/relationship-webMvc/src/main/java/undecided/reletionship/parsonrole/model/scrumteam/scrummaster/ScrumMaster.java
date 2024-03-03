package undecided.reletionship.parsonrole.model.scrumteam.scrummaster;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import undecided.reletionship.parsonrole.model.PersonRole;

@Entity
@Table(schema = "relationship", name = "scrum_masters")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ScrumMaster implements PersonRole<ScrumMaster> {
    @Id
    @Column(name = "scrum_master_id", nullable = false)
    private Long id;

    @Column(name = "employee_no")
    private String employeeNo;

    public static ScrumMaster createAtInsert(Long employeeId, String employeeNo) {
        return new ScrumMaster(employeeId, employeeNo);

    }
}

