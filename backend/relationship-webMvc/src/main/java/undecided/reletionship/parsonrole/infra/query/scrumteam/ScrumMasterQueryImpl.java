package undecided.reletionship.parsonrole.infra.query.scrumteam;

import org.springframework.stereotype.Service;
import undecided.reletionship.parsonrole.business.query.scrumteam.scrummaster.ScrumMasterDto;
import undecided.reletionship.parsonrole.business.query.scrumteam.scrummaster.ScrumMasterQuery;
import undecided.reletionship.parsonrole.model.scrumteam.scrummaster.ScrumMaster;
import undecided.reletionship.parsonrole.model.scrumteam.scrummaster.ScrumMasterRepository;
import undecided.reletionship.party.model.person.Person;
import undecided.reletionship.party.model.person.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScrumMasterQueryImpl implements ScrumMasterQuery {
    private final ScrumMasterRepository scrumMasterRepository;
    private final PersonRepository personRepository;

    public ScrumMasterQueryImpl(ScrumMasterRepository scrumMasterRepository, PersonRepository personRepository) {
        this.scrumMasterRepository = scrumMasterRepository;
        this.personRepository = personRepository;
    }

    @Override
    public ScrumMasterDto findById(Long id) {
        Optional<ScrumMaster> scrumOptional = scrumMasterRepository.findById(id);
        Optional<Person> personOptional = personRepository.findById(scrumOptional.orElseThrow().getId());
        return ScrumMasterDto.reconstruct(scrumOptional.orElseThrow(), personOptional.orElseThrow());
    }

    @Override
    public List<ScrumMasterDto> findAll() {
        return scrumMasterRepository.findAll().stream()
                .map(scrumMaster -> {
                    Optional<Person> person = personRepository.findById(scrumMaster.getId());
                    return ScrumMasterDto.reconstruct(scrumMaster, person.orElseThrow());
                }).collect(Collectors.toList());
    }
}
