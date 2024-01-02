package undecided.adress.model.municipal;

import java.io.IOException;
import java.util.List;

public interface MunicipalsCsvReader {

    List<Municipal> readAll() throws IOException;
}
