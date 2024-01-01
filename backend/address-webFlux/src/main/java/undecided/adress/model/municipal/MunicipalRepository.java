package undecided.adress.model.municipal;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MunicipalRepository extends R2dbcRepository<Municipal, Long> {

    Flux<Municipal> findByPrefectureCode(String prefectureCode);

    Mono<Municipal> findByMunicipalCode(String municipalCode);

}
