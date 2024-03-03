package undecided.projactmgmt.request.appl.command.project;

import org.springframework.stereotype.Service;
import undecided.projactmgmt.request.model.project.Project;
import undecided.projactmgmt.request.model.project.ProjectRepository;

@Service
public class InsertProjectCommand {

    private final ProjectRepository projectRepository;

    public InsertProjectCommand(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;

    }

    public Project execute(Project project) {
        return projectRepository.save(project);

    }
}
