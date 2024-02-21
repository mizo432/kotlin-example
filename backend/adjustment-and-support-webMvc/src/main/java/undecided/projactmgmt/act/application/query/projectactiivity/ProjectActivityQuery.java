package undecided.projactmgmt.act.application.query.projectactiivity;

import undecided.projactmgmt.act.model.ProjectActivity;

import java.util.List;

public interface ProjectActivityQuery {
    List<ProjectActivity> findAll();
    
}
