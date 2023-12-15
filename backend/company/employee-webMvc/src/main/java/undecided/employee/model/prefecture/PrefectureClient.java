package undecided.employee.model.prefecture;

import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface PrefectureClient {

    @GetExchange("/api/v1/prefectures/{prefectureCode}")
    @Observed
    PrefectureDto findByCode(@PathVariable("prefectureCode") String prefectureCode);
}
