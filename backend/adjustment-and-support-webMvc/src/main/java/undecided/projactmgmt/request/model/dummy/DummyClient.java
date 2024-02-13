package undecided.projactmgmt.request.model.dummy;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface DummyClient {
    @GetExchange("/api/v1/dummies")
    List<String> getMessage();

}
