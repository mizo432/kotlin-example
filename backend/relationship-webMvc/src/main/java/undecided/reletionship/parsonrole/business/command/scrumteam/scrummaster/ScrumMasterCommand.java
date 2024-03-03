package undecided.reletionship.parsonrole.business.command.scrumteam.scrummaster;

import org.springframework.stereotype.Service;
import undecided.reletionship.parsonrole.business.query.scrumteam.scrummaster.ScrumMasterDto;
import undecided.reletionship.parsonrole.model.scrumteam.scrummaster.ScrumMaster;
import undecided.reletionship.parsonrole.model.scrumteam.scrummaster.ScrumMasterRepository;
import undecided.reletionship.party.model.person.Person;
import undecided.reletionship.party.model.person.PersonRepository;

@Service
public class ScrumMasterCommand {
    private final ScrumMasterRepository scrumMasterRepository;

    private final PersonRepository personRepository;

    public ScrumMasterCommand(ScrumMasterRepository scrumMasterRepository, PersonRepository personRepository) {
        this.scrumMasterRepository = scrumMasterRepository;

        this.personRepository = personRepository;
    }

    public ScrumMasterDto insert(ScrumMaster scrumMaster) {
        ScrumMaster insertedScrumMaster = scrumMasterRepository.save(scrumMaster);
        Person person = personRepository.findById(insertedScrumMaster.getId()).orElseThrow();

        return ScrumMasterDto.reconstruct(insertedScrumMaster, person);

    }
}
