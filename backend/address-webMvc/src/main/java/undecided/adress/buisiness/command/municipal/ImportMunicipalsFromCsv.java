package undecided.adress.buisiness.command.municipal;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import undecided.adress.model.municipal.Municipal;
import undecided.adress.model.municipal.MunicipalsCsvReader;

import java.io.IOException;

@Service
public class ImportMunicipalsFromCsv {

    private static final Logger log = LoggerFactory.getLogger(ImportMunicipalsFromCsv.class);
    private final ImportMunicipal importMunicipal;
    private final MunicipalsCsvReader municipalsCsvReader;

    public ImportMunicipalsFromCsv(ImportMunicipal importMunicipal, MunicipalsCsvReader municipalsCsvReader) {
        this.importMunicipal = importMunicipal;
        this.municipalsCsvReader = municipalsCsvReader;
    }

    @Observed(name = "ImportMunicipalsFromCsv", contextualName = "importRecs")
    public void importRecs() {
        try {
            log.info("call ImportMunicipalsFromCsv#importRecs()");
            municipalsCsvReader.readAll().toStream().forEach(
                    municipalCsv -> {
                        Municipal municipal = municipalCsv.toEntity();
                        importMunicipal.save(municipal);
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
