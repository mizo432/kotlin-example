package undecided.reletionship.presentation.api.party.person;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.shared.entity.id.SnowflakeId;
import undecided.reletionship.business.command.PersonCommand;
import undecided.reletionship.business.query.person.PersonQuery;

@RestController
@RequestMapping("/api/v1/people")
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
    public Flux<PersonDto> get() {
        log.info("called get() method.");
        return personQuery.findAll().map(PersonDto::convertFrom);

    }

    @GetMapping("/{id}")
    @Observed
    public Mono<PersonDto> get(@PathVariable Long id) {
        log.info("called get() method.");
        return personQuery.findById(id).map(PersonDto::convertFrom);

    }

    @PostMapping
    @Observed
    public Mono<PersonDto> post(@RequestBody PersonDto personDto) {
        log.info("called post() method.");
        return personCommand.insert(personDto.convertToEntityAtInsert())
                .map(PersonDto::convertFrom);

    }

    @PutMapping("/{id}")
    @Observed
    public Mono<PersonDto> put(@PathVariable Long id, @RequestBody PersonDto personDto) {
        log.info("called put() method.");
        return personCommand.update(id, personDto.convertToEntityAtUpdate())
                .map(PersonDto::convertFrom);

    }

    @DeleteMapping("/{id}")
    @Observed
    public Mono<Void> delete(@PathVariable Long id) {
        log.info("called delete() method.");
        return personCommand.delete(SnowflakeId.of(id).getValue());

    }
}
