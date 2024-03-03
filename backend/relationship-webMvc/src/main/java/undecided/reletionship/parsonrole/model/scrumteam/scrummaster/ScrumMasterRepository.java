package undecided.reletionship.parsonrole.model.scrumteam.scrummaster;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Observed
public interface ScrumMasterRepository extends JpaRepository<ScrumMaster, Long> {

}
