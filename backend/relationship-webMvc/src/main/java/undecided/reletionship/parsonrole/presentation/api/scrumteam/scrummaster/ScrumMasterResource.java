package undecided.reletionship.parsonrole.presentation.api.scrumteam.scrummaster;

import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.*;
import undecided.reletionship.parsonrole.business.command.scrumteam.scrummaster.ScrumMasterCommand;
import undecided.reletionship.parsonrole.business.query.scrumteam.scrummaster.ScrumMasterDto;
import undecided.reletionship.parsonrole.business.query.scrumteam.scrummaster.ScrumMasterQuery;

import java.util.List;

@RestController
@RequestMapping("/person-role/api/v1/scrum/scrum-masters")
public class ScrumMasterResource {
    private final ScrumMasterQuery scrumMasterQuery;
    private final ScrumMasterCommand scrumMasterCommand;

    public ScrumMasterResource(ScrumMasterQuery scrumMasterQuery, ScrumMasterCommand scrumMasterCommand) {
        this.scrumMasterQuery = scrumMasterQuery;
        this.scrumMasterCommand = scrumMasterCommand;
    }

    @GetMapping
    @Observed
    public List<ScrumMasterDto> get() {
        return scrumMasterQuery.findAll();

    }

    @GetMapping("/{scrumMasterId}")
    @Observed
    public ScrumMasterDto get(@PathVariable("scrumMasterId") Long scrumMasterId) {
        return scrumMasterQuery.findById(scrumMasterId);

    }

    @PostMapping()
    @Observed
    public ScrumMasterDto post(@RequestBody ScrumMasterDto scrumMasterDto) {
        return scrumMasterCommand.insert(scrumMasterDto.convertToEntity());

    }

}
