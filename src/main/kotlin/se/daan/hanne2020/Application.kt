package se.daan.hanne2020

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.Clock

@SpringBootApplication
@ConfigurationPropertiesScan
class Application {
    @Bean
    fun clock() = Clock.systemUTC()
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
