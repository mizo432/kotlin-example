package com.undecided.address.infra.datasource.city;

import com.undecided.address.buisiness.query.city.CityDto;
import com.undecided.address.buisiness.query.city.CityQuery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityQueryImpl implements CityQuery {
    @Override
    public Flux<CityDto> selectByPrefectureCode(String prefectureCode) {
        return Flux.empty();
    }

    @Override
    public Flux<CityDto> selectAll() {
        return Flux.empty();
    }

    @Override
    public Mono<CityDto> findOneByCityCode(String cityCode) {
        return Mono.empty();
    }
}
