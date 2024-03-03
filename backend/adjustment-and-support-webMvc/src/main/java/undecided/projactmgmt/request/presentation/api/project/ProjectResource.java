package undecided.projactmgmt.request.presentation.api.project;

import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import undecided.projactmgmt.request.appl.command.project.InsertProjectCommand;
import undecided.projactmgmt.request.appl.query.project.ProjectDto;
import undecided.projactmgmt.request.appl.query.project.ProjectQuery;

import java.util.List;

@RestController
@RequestMapping("/project/api/v1/projects")
public class ProjectResource {
    private final ProjectQuery projectQuery;

    private final InsertProjectCommand insertProjectCommand;

    public ProjectResource(ProjectQuery projectQuery, InsertProjectCommand insertProjectCommand) {
        this.projectQuery = projectQuery;
        this.insertProjectCommand = insertProjectCommand;
    }


    @RequestMapping
    @Observed
    public List<ProjectDto> getAny() {
        return projectQuery.findAll();

    }

    @RequestMapping(method = RequestMethod.POST)
    @Observed
    public ProjectDto post(@RequestBody ProjectDto projectDto) {
        return ProjectDto.convertFrom(insertProjectCommand.execute(projectDto.convertToEntity()));

    }

}
