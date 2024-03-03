package undecided.projactmgmt.request.presentation.api.project;

import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import undecided.projactmgmt.request.appl.query.project.ProjectDto;
import undecided.projactmgmt.request.appl.query.project.ProjectQuery;

import java.util.List;

@RestController
@RequestMapping("/project/api/v1/projects")
public class ProjectResource {
    private final ProjectQuery projectQuery;

    public ProjectResource(ProjectQuery projectQuery) {
        this.projectQuery = projectQuery;
    }


    @RequestMapping
    @Observed
    public List<ProjectDto> getAny() {
        return projectQuery.findAll();

    }

}
