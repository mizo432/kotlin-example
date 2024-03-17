package undecided.allInOneWebFlux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class AllInOneWebFluxApplication

fun main(args: Array<String>) {
    runApplication<AllInOneWebFluxApplication>(*args)
}

