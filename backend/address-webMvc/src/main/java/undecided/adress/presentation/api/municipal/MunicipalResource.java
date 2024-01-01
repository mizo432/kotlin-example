package undecided.adress.presentation.api.municipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import undecided.adress.buisiness.command.municipal.ImportMunicipalsFromCsv;
import undecided.adress.buisiness.query.municipal.MunicipalQuery;

import java.util.List;

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
    public List<MunicipalDto> get() {
        return MunicipalDto.reconstruct(municipalQuery.selectAll());
    }

    @GetMapping(path = "/{municipalCode}", produces = {"application/json"})
    public MunicipalDto getByCode(@PathVariable("municipalCode") String municipalCode) {

        return MunicipalDto.reconstruct(municipalQuery.findOneByMunicipalCode(municipalCode));
    }

    @PostMapping(path = "/import")
    public void importFromCsv() {
        log.info("MunicipalResource#" + "importFromCsv()");
        importMunicipalsFromCsv.importRecs();

    }

}
