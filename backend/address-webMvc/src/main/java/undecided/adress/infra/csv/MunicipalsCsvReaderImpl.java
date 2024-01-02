package undecided.adress.infra.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import undecided.adress.buisiness.command.municipal.MunicipalCsv;
import undecided.adress.model.municipal.Municipal;
import undecided.adress.model.municipal.MunicipalsCsvReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MunicipalsCsvReaderImpl implements MunicipalsCsvReader {
    private static final Logger log = LoggerFactory.getLogger(MunicipalsCsvReaderImpl.class);

    @Override
    public List<Municipal> readAll() throws IOException {
        List<MunicipalCsv> readCsv = readCsvAll();
        log.info("readCsv:" + readCsv);
        List<Municipal> result = new ArrayList<>();
        for (MunicipalCsv municipalCsv : readCsv) {
            result.add(municipalCsv.toEntity());
        }
        return result;
    }

    private List<MunicipalCsv> readCsvAll() throws IOException {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
        Path csvFilePath = Path.of("./address-webFlux/src/main/resources/data/csv/mt_city_all.csv");
        log.info("file(.):" + new File(".").getAbsolutePath());
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.registerModule(javaTimeModule);
        CsvSchema csvSchema = csvMapper
                .schemaFor(MunicipalCsv.class)
                .withHeader();
        List<MunicipalCsv> rtn = new ArrayList<>();

        try (MappingIterator<MunicipalCsv> objectMappingIterator = csvMapper.readerFor(MunicipalCsv.class)
                .with(csvSchema)
                .readValues(csvFilePath.toFile())) {

            while (objectMappingIterator.hasNext()) {
                rtn.add(objectMappingIterator.next());
            }
        }

        return rtn;
    }
}
