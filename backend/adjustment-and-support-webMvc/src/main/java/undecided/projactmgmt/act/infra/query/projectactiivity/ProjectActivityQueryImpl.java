package undecided.projactmgmt.act.infra.query.projectactiivity;

import org.springframework.stereotype.Service;
import undecided.projactmgmt.act.application.query.projectactiivity.ProjectActivityQuery;
import undecided.projactmgmt.act.model.ProjectActivity;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectActivityQueryImpl implements ProjectActivityQuery {
    public List<ProjectActivity> findAll() {
        return new ArrayList<>();

    }

}
