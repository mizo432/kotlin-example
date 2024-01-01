package undecided.adress.infra.query.municipal;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import undecided.adress.buisiness.query.municipal.MunicipalQuery;
import undecided.adress.model.municipal.Municipal;
import undecided.adress.model.municipal.MunicipalRepository;

import java.util.List;

@Service
public class MunicipalQueryImpl implements MunicipalQuery {

    private final MunicipalRepository municipalRepository;

    public MunicipalQueryImpl(MunicipalRepository municipalRepository) {
        this.municipalRepository = municipalRepository;
    }

    @Override
    @Observed
    public List<Municipal> selectByPrefectureCode(String prefectureCode) {
        return municipalRepository.findByPrefectureCode(prefectureCode);

    }

    @Override
    @Observed
    public List<Municipal> selectAll() {
        return municipalRepository.findAll();

    }

    @Override
    @Observed
    public Municipal findOneByMunicipalCode(String municipalCode) {
        return municipalRepository.findByMunicipalCode(municipalCode);

    }
}
