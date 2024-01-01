package undecided.adress.model.municipal;

import reactor.core.publisher.Flux;
import undecided.adress.buisiness.command.municipal.MunicipalCsv;

import java.io.IOException;

public interface MunicipalsCsvReader {

    Flux<MunicipalCsv> readAll() throws IOException;
}
