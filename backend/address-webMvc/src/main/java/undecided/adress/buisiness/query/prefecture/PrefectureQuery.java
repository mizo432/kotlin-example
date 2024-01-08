package undecided.adress.buisiness.query.prefecture;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.adress.model.prefecture.Prefecture;

import static java.util.Objects.nonNull;

@Service
@Observed
public class PrefectureQuery {
    public Flux<PrefectureDto> findAll() {
        return Flux.just(PrefectureDto.PrefecturesDto.convertFrom(Prefecture.values()));

    }

    public Mono<PrefectureDto> findByCode(String prefectureCode) {
        Prefecture prefecture = Prefecture.valueOfCode(prefectureCode);
        if (nonNull(prefecture))
            return Mono.just(new PrefectureDto(prefecture.getCode(), prefecture.getName()));
        return Mono.empty();

    }
}
