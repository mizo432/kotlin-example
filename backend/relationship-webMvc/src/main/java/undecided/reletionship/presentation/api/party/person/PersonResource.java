package undecided.reletionship.presentation.api.party.person;

import org.springframework.web.bind.annotation.*;
import undecided.reletionship.business.command.PersonCommand;
import undecided.reletionship.business.query.person.PersonQuery;
import undecided.shared.entity.id.SnowflakeId;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonResource {
    private final PersonQuery personQuery;
    private final PersonCommand personCommand;

    public PersonResource(PersonQuery personQuery, PersonCommand personCommand) {
        this.personQuery = personQuery;
        this.personCommand = personCommand;
    }

    @GetMapping
    public List<PersonDto> get() {
        return PersonDto.convertFrom(personQuery.findAll());

    }

    @GetMapping("/{id}")
    public PersonDto get(@PathVariable Long id) {
        return PersonDto.convertFrom(personQuery.findById(SnowflakeId.of(id)));

    }

    @PostMapping
    public PersonDto post(@RequestBody PersonDto personDto) {
        return PersonDto.convertFrom(personCommand.insert(personDto.convertToEntityAtInsert()));

    }

    @PutMapping("/{id}")
    public PersonDto put(@PathVariable Long id, @RequestBody PersonDto personDto) {
        return PersonDto.convertFrom(personCommand.update(SnowflakeId.of(id), personDto.convertToEntityAtUpdate()));

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personCommand.delete(SnowflakeId.of(id));

    }
}
