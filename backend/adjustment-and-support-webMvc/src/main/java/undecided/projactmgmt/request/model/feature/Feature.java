package undecided.projactmgmt.request.model.feature;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "features", schema = "adjustment", uniqueConstraints = {
        @UniqueConstraint(name = "UK1_feature", columnNames = {"project_id", "feature_code"})
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Feature {
    @Id
    @Column(name = "feature_id")
    private Long id;
    @Column(nullable = false)
    private Long projectId;
    @Column(length = 100, name = "feature_code")
    private String code;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(length = 3000)
    String notes;

}
