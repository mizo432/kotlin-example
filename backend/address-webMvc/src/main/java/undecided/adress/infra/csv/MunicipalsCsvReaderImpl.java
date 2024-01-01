package undecided.adress.infra.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import undecided.adress.buisiness.command.municipal.MunicipalCsv;
import undecided.adress.model.municipal.MunicipalsCsvReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class MunicipalsCsvReaderImpl implements MunicipalsCsvReader {
    private static final Logger log = LoggerFactory.getLogger(MunicipalsCsvReaderImpl.class);

    @Override
    public List<MunicipalCsv> readAll() throws IOException {
        Path csvFilePath = Path.of("./address-webFlux/src/main/resources/data/csv/mt_city_all.csv");
        log.info("file(.):" + new File(".").getAbsolutePath());

        log.info("path:" + csvFilePath);
        Stream<String> lineStream = Files.lines(csvFilePath);

        return new ArrayList<>();
    }
}
