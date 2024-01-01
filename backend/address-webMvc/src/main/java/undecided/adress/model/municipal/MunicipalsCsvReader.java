package undecided.adress.model.municipal;

import undecided.adress.buisiness.command.municipal.MunicipalCsv;

import java.io.IOException;
import java.util.List;

public interface MunicipalsCsvReader {

    List<MunicipalCsv> readAll() throws IOException;
}
