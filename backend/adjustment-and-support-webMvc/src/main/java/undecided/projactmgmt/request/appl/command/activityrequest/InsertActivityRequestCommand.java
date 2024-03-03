package undecided.projactmgmt.request.appl.command.activityrequest;

import org.springframework.stereotype.Service;
import undecided.projactmgmt.request.model.project.Project;
import undecided.projactmgmt.request.model.project.ProjectRepository;

@Service
public class InsertActivityRequestCommand {

    private final ProjectRepository projectRepository;

    public InsertActivityRequestCommand(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project execute(Project project) {
        return projectRepository.save(project);
        
    }
}
