package undecided.adress.buisiness.command.municipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import undecided.adress.model.municipal.Municipal;
import undecided.adress.model.municipal.MunicipalRepository;

@Service
public class ImportMunicipal {

    private static final Logger log = LoggerFactory.getLogger(ImportMunicipal.class);

    private final MunicipalRepository municipalRepository;

    public ImportMunicipal(MunicipalRepository municipalRepository) {
        this.municipalRepository = municipalRepository;
    }

    public Municipal save(Municipal municipal) {
        log.info("ImportMunicipal#" + "save(Municipal)");
        return municipalRepository.save(municipal);
    }

}
