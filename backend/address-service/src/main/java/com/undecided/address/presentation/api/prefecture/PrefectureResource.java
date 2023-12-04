package com.undecided.address.presentation.api.prefecture;

import com.undecided.address.model.prefecture.Prefecture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping(path = "/api/v1/prefectures")
public class PrefectureResource {
    @RequestMapping("")
    public Flux<PrefectureDto> selectAll() {
        return Flux.just(PrefecturesDto.convertFrom(Prefecture.values()));
    }

    @RequestMapping("/{prefectureCode}")
    public Mono<PrefectureDto> findByCode(@PathVariable("prefectureCode") String prefectureCode) {
        Prefecture prefecture = Prefecture.valueOfCode(prefectureCode);
        if (nonNull(prefecture))
            return Mono.empty();
        return Mono.just(new PrefectureDto(prefecture.getCode(), prefecture.getName()));
    }
}
