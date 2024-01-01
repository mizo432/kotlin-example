package undecided.adress.buisiness.query.municipal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.adress.model.municipal.Municipal;

public interface MunicipalQuery {
    Flux<Municipal> selectByPrefectureCode(String prefectureCode);

    Flux<Municipal> selectAll();

    Mono<Municipal> findOneByMunicipalCode(String cityCode);

}
