package undecided.adress.presentation.api.municipal;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.adress.buisiness.query.municipal.MunicipalDto;
import undecided.adress.buisiness.query.municipal.MunicipalQuery;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path = "/api/v1/address/municipals")
public class MunicipalResource {
    private final MunicipalQuery municipalQuery;

    public MunicipalResource(MunicipalQuery municipalQuery) {
        this.municipalQuery = municipalQuery;
    }

    @GetMapping(path = "", produces = {"application/json"})
    public Flux<MunicipalDto> selectByQuery(@RequestParam(required = false) String prefectureCode) {
        if (isNull(prefectureCode)) {
            return municipalQuery.selectAll();

        }
        return municipalQuery.selectByPrefectureCode(prefectureCode);
    }

    @GetMapping(path = "{municipalCode}", produces = {"application/json"})
    public Mono<MunicipalDto> findByCode(@PathVariable("municipalCode") String municipalCode) {
        return municipalQuery.findOneByMunicipalCode(municipalCode);
    }
}
