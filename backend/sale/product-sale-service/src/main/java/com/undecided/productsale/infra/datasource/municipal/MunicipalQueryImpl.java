package com.undecided.productsale.infra.datasource.municipal;

import com.undecided.productsale.business.query.any.MunicipalDto;
import com.undecided.productsale.business.query.any.MunicipalDto.MunicipalsDto;
import com.undecided.productsale.business.query.any.MunicipalQuery;
import com.undecided.productsale.model.municipal.Municipal;
import com.undecided.productsale.model.municipal.Municipal.Municipals;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipalQueryImpl implements MunicipalQuery {

    private static final List<Municipal> source = new ArrayList<>();

    public MunicipalQueryImpl() {
        source.add(Municipal.create(1L, 1L, "001", "札幌市"));
        source.add(Municipal.create(2L, 1L, "002", "帯広市"));
        source.add(Municipal.create(3L, 13L, "001", "千葉市"));
    }

    @Override
    public Flux<MunicipalDto> selectByPrefectureCode(String prefectureCode) {
        List<Municipal> result = source.stream().filter(municipal -> municipal.getPrefectureId().equals(Long.valueOf(prefectureCode))).collect(Collectors.toList());
        return Flux.just(MunicipalsDto.convertFrom(Municipals.of(result)).toArray(new MunicipalDto[]{}));
    }

    @Override
    public Flux<MunicipalDto> selectAll() {
        return Flux.just(MunicipalsDto.convertFrom(Municipals.of(source)).toArray(new MunicipalDto[]{}));
    }

    @Override
    public Mono<MunicipalDto> findOneByMunicipalCode(String cityCode) {
        Municipal result = source.stream().filter(
                municipal -> municipal.getMunicipalId().equals(Long.valueOf(cityCode))).findFirst().orElseThrow();
        return Mono.just(MunicipalDto.convertFrom(result));
    }
}
