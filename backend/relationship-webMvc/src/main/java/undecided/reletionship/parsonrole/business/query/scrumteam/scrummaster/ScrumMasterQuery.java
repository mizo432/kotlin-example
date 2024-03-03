package undecided.reletionship.parsonrole.business.query.scrumteam.scrummaster;

import java.util.List;

public interface ScrumMasterQuery {
    ScrumMasterDto findById(Long id);

    List<ScrumMasterDto> findAll();


}
