package undecided.adress.presentation.api.municipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.adress.buisiness.command.municipal.ImportMunicipalsFromCsv;
import undecided.adress.buisiness.query.municipal.MunicipalQuery;

@RestController
@RequestMapping(path = "/api/v1/address/municipals")
public class MunicipalResource {
    private final static Logger log = LoggerFactory.getLogger(MunicipalResource.class);
    private final MunicipalQuery municipalQuery;
    private final ImportMunicipalsFromCsv importMunicipalsFromCsv;

    public MunicipalResource(MunicipalQuery municipalQuery, ImportMunicipalsFromCsv importMunicipalsFromCsv) {
        this.municipalQuery = municipalQuery;
        this.importMunicipalsFromCsv = importMunicipalsFromCsv;
    }

    @GetMapping(path = "", produces = {"application/json"})
    public Flux<MunicipalDto> get() {
        return municipalQuery.selectAll()
                .map(MunicipalDto::reconstruct);
    }

    @GetMapping(path = "/{municipalCode}", produces = {"application/json"})
    public Mono<MunicipalDto> getByCode(@PathVariable("municipalCode") String municipalCode) {

        return municipalQuery.findOneByMunicipalCode(municipalCode)
                .map(MunicipalDto::reconstruct);
    }

    @PostMapping(path = "/import")
    public void importFromCsv() {
        log.info("MunicipalResource#" + "importFromCsv()");
        importMunicipalsFromCsv.importRecs();

    }

}
