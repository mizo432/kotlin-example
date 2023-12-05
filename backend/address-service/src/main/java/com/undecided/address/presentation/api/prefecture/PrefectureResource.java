package com.undecided.address.presentation.api.prefecture;

import com.undecided.address.model.prefecture.Prefecture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping(path = "/api/v1/prefectures")
@Slf4j
public class PrefectureResource {
    @RequestMapping("")
    public Flux<PrefectureDto> selectAll() {
        log.info("called get all Prefectures.");
        return Flux.just(PrefecturesDto.convertFrom(Prefecture.values()));

    }

    @RequestMapping("/{prefectureCode}")
    public Mono<PrefectureDto> findByCode(@PathVariable("prefectureCode") String prefectureCode) {
        log.info("called get a Prefecture by prefectureCode.");
        Prefecture prefecture = Prefecture.valueOfCode(prefectureCode);
        if (nonNull(prefecture))
            return Mono.empty();
        return Mono.just(new PrefectureDto(prefecture.getCode(), prefecture.getName()));
    }
}
