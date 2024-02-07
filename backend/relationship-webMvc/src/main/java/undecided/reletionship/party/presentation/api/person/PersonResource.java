package undecided.reletionship.party.presentation.api.person;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import undecided.reletionship.party.business.command.person.PersonCommand;
import undecided.reletionship.party.business.query.person.PersonQuery;
import undecided.shared.entity.id.SnowflakeId;

import java.util.List;

@RestController
@RequestMapping("/party/api/v1/people")
public class PersonResource {
    private static final Logger log = LoggerFactory.getLogger(PersonResource.class);
    private final PersonQuery personQuery;
    private final PersonCommand personCommand;

    public PersonResource(PersonQuery personQuery, PersonCommand personCommand) {
        this.personQuery = personQuery;
        this.personCommand = personCommand;
    }

    @GetMapping
    @Observed
    public List<PersonDto> get() {
        log.info("called get() method.");
        return PersonDto.reconstruct(personQuery.findAll());

    }

    @GetMapping("/{id}")
    @Observed
    public PersonDto get(@PathVariable Long id) {
        log.info("called get() method.");
        return PersonDto.reconstruct(personQuery.findById(id));

    }

    @PostMapping
    @Observed
    public PersonDto post(@RequestBody PersonDto personDto) {
        log.info("called post() method.");
        return PersonDto.reconstruct(personCommand.insert(personDto.convertToEntityAtInsert()));

    }

    @PutMapping("/{id}")
    @Observed
    public PersonDto put(@PathVariable Long id, @RequestBody PersonDto personDto) {
        log.info("called put() method.");
        return PersonDto.reconstruct(personCommand.update(id, personDto.convertToEntityAtUpdate()));
    }

    @DeleteMapping("/{id}")
    @Observed
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("called delete() method.");
        personCommand.delete(SnowflakeId.of(id).getValue());

    }
}
