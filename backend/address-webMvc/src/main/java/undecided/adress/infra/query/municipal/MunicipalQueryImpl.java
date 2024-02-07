package undecided.adress.infra.query.municipal;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import undecided.adress.buisiness.query.municipal.MunicipalQuery;
import undecided.adress.model.municipal.Municipal;
import undecided.adress.model.municipal.MunicipalRepository;

import java.util.List;

@Service
public class MunicipalQueryImpl implements MunicipalQuery {

    private static final Logger log = LoggerFactory.getLogger(MunicipalQueryImpl.class);

    private final MunicipalRepository municipalRepository;

    public MunicipalQueryImpl(MunicipalRepository municipalRepository) {
        this.municipalRepository = municipalRepository;
    }

    @Override
    @Observed
    public List<Municipal> findAll() {
        log.info("CALL findAll()");
        return municipalRepository.findAll();

    }

    @Override
    @Observed
    public Municipal findOneByMunicipalCode(String municipalCode) {
        log.info("CALL findOneByMunicipalCode(String)");
        return municipalRepository.findByMunicipalCode(municipalCode);

    }

    @Override
    @Observed
    public List<Municipal> findByPrefectureCode(String prefectureCode) {
        log.info("CALL findByPrefectureCode(String)");
        return municipalRepository.findByPrefectureCode(prefectureCode);

    }
}
