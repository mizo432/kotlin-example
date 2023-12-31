package undecided.adress.presentation.api.prefecture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.adress.model.prefecture.Prefecture;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping(path = "/api/v1/address/prefectures")
public class PrefectureResource {
    private final static Logger log = LoggerFactory.getLogger(PrefectureResource.class);

    @RequestMapping("")
    public Flux<PrefectureDto> getAll() {
        log.info("get all Prefectures.");
        return Flux.just(PrefecturesDto.convertFrom(Prefecture.values()));

    }

    @RequestMapping("/{prefectureCode}")
    public Mono<PrefectureDto> getByCode(@PathVariable("prefectureCode") String prefectureCode) {
        log.info("get a Prefecture by prefectureCode.");
        Prefecture prefecture = Prefecture.valueOfCode(prefectureCode);
        if (nonNull(prefecture))
            return Mono.just(new PrefectureDto(prefecture.getCode(), prefecture.getName()));
        return Mono.empty();
    }
}
