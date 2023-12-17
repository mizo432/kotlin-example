package undicided.reletionship.business.query.person;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undicided.reletionship.model.party.person.Person;

@Service
public interface PersonQuery {
    Flux<Person> findAll();

    Mono<Person> findById(Long id);
    
}
