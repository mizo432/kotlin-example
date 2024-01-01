package undecided.adress.infra.query.municipal;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.adress.buisiness.query.municipal.MunicipalQuery;
import undecided.adress.model.municipal.Municipal;
import undecided.adress.model.municipal.MunicipalRepository;

@Service
public class MunicipalQueryImpl implements MunicipalQuery {

    private final MunicipalRepository municipalRepository;

    public MunicipalQueryImpl(MunicipalRepository municipalRepository) {
        this.municipalRepository = municipalRepository;
    }

    @Override
    public Flux<Municipal> selectByPrefectureCode(String prefectureCode) {
        return municipalRepository.findByPrefectureCode(prefectureCode);

    }

    @Override
    public Flux<Municipal> selectAll() {
        return municipalRepository.findAll();

    }

    @Override
    public Mono<Municipal> findOneByMunicipalCode(String municipalCode) {
        return municipalRepository.findByMunicipalCode(municipalCode);

    }
}
