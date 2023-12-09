package undecided.employee.infra.client.prefecture;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Mono;
import undecided.employee.model.prefecture.PrefectureClient;
import undecided.employee.model.prefecture.PrefectureDto;

@Component
public class PrefectureClientImpl implements PrefectureClient {
    private final RestClient restClient;

    public PrefectureClientImpl(@Qualifier("addressRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Mono<PrefectureDto> findByCode(String prefectureCode) {
        PrefectureDto prefecture = restClient.get()
                .uri("/api/v1/Prefecture/{prefectureCode}", prefectureCode)
                .retrieve()
                .body(PrefectureDto.class);

        if (prefecture == null) {
            return Mono.empty();
        }

        return Mono.just(prefecture);
    }
}
