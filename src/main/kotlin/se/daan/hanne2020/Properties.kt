package se.daan.hanne2020

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant

@ConstructorBinding
@ConfigurationProperties("hanne")
data class Properties(
    @DateTimeFormat
    val release: Instant,
    val dateOfBirth: String
)