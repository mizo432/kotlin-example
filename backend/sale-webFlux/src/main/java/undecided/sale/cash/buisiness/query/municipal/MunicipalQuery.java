package undecided.sale.cash.buisiness.query.municipal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MunicipalQuery {
    Flux<MunicipalDto> selectByPrefectureCode(String prefectureCode);

    Flux<MunicipalDto> selectAll();

    Mono<MunicipalDto> findOneByMunicipalCode(String cityCode);

}
