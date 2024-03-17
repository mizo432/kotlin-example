package undecided.relationship.partyrole.customer.presentation.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import undecided.relationship.partyrole.customer.CustomerDto

@RestController
@RequestMapping(path = ["api/v1/customers"])
class CustomerResource {

    @GetMapping
    fun getAll(): Flux<CustomerDto> = Flux.empty();
}