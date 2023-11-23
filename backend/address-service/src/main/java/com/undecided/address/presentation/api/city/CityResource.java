package com.undecided.address.presentation.api.city;

import com.undecided.address.buisiness.query.city.CityDto;
import com.undecided.address.buisiness.query.city.CityQuery;
import io.netty.handler.codec.http.HttpContent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path = "/api/v1/cities")
public class CityResource {
    private final CityQuery cityQuery;

    public CityResource(CityQuery cityQuery) {
        this.cityQuery = cityQuery;
    }

    @GetMapping(path = "",produces = {"application/json"})
    public Flux<CityDto> selectByQuery(@RequestParam(required = false) String prefectureCode){
        if(isNull( prefectureCode)) {
            return cityQuery.selectAll();

        }
        return cityQuery.selectByPrefectureCode(prefectureCode);
    }

    @GetMapping(path = "{cityCode}",produces = {"application/json"})
    public Mono<CityDto> findByCode(@PathVariable("cityCode") String cityCode){
        return cityQuery.findOneByCityCode(cityCode);
    }
}
