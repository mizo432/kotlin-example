package undecided.projactmgmt.request.infra.query.project;

import org.springframework.stereotype.Service;
import undecided.projactmgmt.request.appl.query.project.ProjectDto;
import undecided.projactmgmt.request.appl.query.project.ProjectQuery;

import java.util.List;

@Service
public class ProjectQueryImpl implements ProjectQuery {

    @Override
    public List<ProjectDto> findAll() {
        return null;
    }
}
