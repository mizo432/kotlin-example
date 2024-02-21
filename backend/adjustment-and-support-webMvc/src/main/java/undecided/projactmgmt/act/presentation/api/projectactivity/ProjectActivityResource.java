package undecided.projactmgmt.act.presentation.api.projectactivity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import undecided.projactmgmt.act.application.query.projectactiivity.ProjectActivityQuery;
import undecided.projactmgmt.act.model.ProjectActivity;

import java.util.List;

@RestController
@RequestMapping(path = "/project/api/v1/project-activities")
public class ProjectActivityResource {

    private final ProjectActivityQuery projectActivityQuery;

    public ProjectActivityResource(ProjectActivityQuery projectActivityQuery) {
        this.projectActivityQuery = projectActivityQuery;
    }

    @GetMapping
    public List<ProjectActivity> getAny() {
        return projectActivityQuery.findAll();

    }
}
