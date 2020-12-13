package se.daan.hanne2020

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException
import se.daan.hanne2020.page.Pages
import java.time.Clock

@Controller
class Controller(
    private val clock: Clock,
    private val properties: Properties,
    private val pages: Pages
) {
    @GetMapping
    fun index(@RequestParam("dob", required = false) dateOfBirth: String?): String {
        checkRelease()
        return if(dateOfBirth != null && dateOfBirth == properties.dateOfBirth) {
            pages.getCurrentPage().template
        } else {
            "index"
        }

    }

    @GetMapping("/redeem")
    fun redeem(@RequestParam("code", required = false) code: String?): String {
        checkRelease()
        if(code == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        return "test"
    }

    private fun checkRelease() {
        if(clock.instant().isBefore(properties.release)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }
}