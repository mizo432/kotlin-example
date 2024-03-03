package undecided.projactmgmt.request.appl.query.project;

import undecided.projactmgmt.request.model.project.Project;

public class ProjectDto {
    public static ProjectDto convertFrom(Project project) {
        return new ProjectDto();

    }

    public Project convertToEntity() {
        return null;

    }
}
