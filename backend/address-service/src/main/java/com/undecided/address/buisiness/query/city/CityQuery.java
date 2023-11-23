package com.undecided.address.buisiness.query.city;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityQuery {
    Flux<CityDto> selectByPrefectureCode(String prefectureCode);

    Flux<CityDto> selectAll();

    Mono<CityDto> findOneByCityCode(String cityCode);

}
