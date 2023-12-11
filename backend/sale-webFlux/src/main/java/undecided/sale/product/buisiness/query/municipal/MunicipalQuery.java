package undecided.sale.product.buisiness.query.municipal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.sale.cash.buisiness.query.municipal.MunicipalDto;

public interface MunicipalQuery {
    Flux<MunicipalDto> selectByPrefectureCode(String prefectureCode);

    Flux<MunicipalDto> selectAll();

    Mono<MunicipalDto> findOneByMunicipalCode(String cityCode);

}
