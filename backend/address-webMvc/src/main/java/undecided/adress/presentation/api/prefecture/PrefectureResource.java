package undecided.adress.presentation.api.prefecture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.adress.buisiness.query.prefecture.PrefectureDto;
import undecided.adress.buisiness.query.prefecture.PrefectureQuery;

@RestController
@RequestMapping(path = "/api/v1/prefectures")
public class PrefectureResource {
    private final static Logger log = LoggerFactory.getLogger(PrefectureResource.class);
    private final PrefectureQuery prefectureQuery;

    public PrefectureResource(PrefectureQuery prefectureQuery) {
        this.prefectureQuery = prefectureQuery;
    }

    @RequestMapping("")
    public Flux<PrefectureDto> getAll() {
        log.info("get all Prefectures.");
        return prefectureQuery.findAll();

    }

    @RequestMapping("/{prefectureCode}")
    public Mono<PrefectureDto> getByCode(@PathVariable("prefectureCode") String prefectureCode) {
        log.info("get a Prefecture by prefectureCode.");
        return prefectureQuery.findByCode(prefectureCode);
    }
}
