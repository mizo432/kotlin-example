package undecided.sale.cash.infra.datasource.municipal;

import undecided.sale.cash.buisiness.query.municipal.MunicipalDto;
import undecided.sale.cash.buisiness.query.municipal.MunicipalDto.MunicipalsDto;
import undecided.sale.cash.buisiness.query.municipal.MunicipalQuery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.sale.cash.model.municipal.Municipal;

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
        return Flux.just(MunicipalsDto.convertFrom(Municipal.Municipals.of(result)).toArray(new MunicipalDto[]{}));
    }

    @Override
    public Flux<MunicipalDto> selectAll() {
        return Flux.just(MunicipalsDto.convertFrom(Municipal.Municipals.of(source)).toArray(new MunicipalDto[]{}));
    }

    @Override
    public Mono<MunicipalDto> findOneByMunicipalCode(String cityCode) {
        Municipal result = source.stream().filter(
                municipal -> municipal.getMunicipalId().equals(Long.valueOf(cityCode))).findFirst().orElseThrow();
        return Mono.just(MunicipalDto.convertFrom(result));
    }
}
