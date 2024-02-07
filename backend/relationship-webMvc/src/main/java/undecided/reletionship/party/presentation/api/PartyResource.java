package undecided.reletionship.party.presentation.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("party/api/v1/parties")
public class PartyResource {

    private static final Logger log = LoggerFactory.getLogger(PartyResource.class);


    @GetMapping
    String hello() {
        return "hello";

    }

}
