package undecided.adress.presentation.api.municipal;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import undecided.adress.buisiness.command.municipal.ImportMunicipalsFromCsv;
import undecided.adress.buisiness.query.municipal.MunicipalDto;
import undecided.adress.buisiness.query.municipal.MunicipalQuery;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/municipals")
public class MunicipalResource {
    private final static Logger log = LoggerFactory.getLogger(MunicipalResource.class);
    private final MunicipalQuery municipalQuery;
    private final ImportMunicipalsFromCsv importMunicipalsFromCsv;

    public MunicipalResource(MunicipalQuery municipalQuery, ImportMunicipalsFromCsv importMunicipalsFromCsv) {
        this.municipalQuery = municipalQuery;
        this.importMunicipalsFromCsv = importMunicipalsFromCsv;
    }


    @GetMapping("/{municipalCode}")
    public MunicipalDto getByCode(@PathVariable("municipalCode") String municipalCode) {


        return MunicipalDto.reconstruct(municipalQuery.findOneByMunicipalCode(municipalCode));
    }

    @GetMapping
    public List<MunicipalDto> findByPrefectureCode(@RequestParam(value = "prefectureCode", required = false) String prefectureCode) {
        if (Strings.isNullOrEmpty(prefectureCode))
            return MunicipalDto.reconstruct(municipalQuery.findAll());

        return MunicipalDto.reconstruct(municipalQuery.findByPrefectureCode(prefectureCode));
    }

    @PostMapping(path = "/import")
    public void importFromCsv() {
        log.info("MunicipalResource#" + "importFromCsv()");
        importMunicipalsFromCsv.importRecs();

    }

}
