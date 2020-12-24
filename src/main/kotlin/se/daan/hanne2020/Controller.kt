package se.daan.hanne2020

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.view.RedirectView
import se.daan.hanne2020.page.Pages
import se.daan.hanne2020.redeem.Redeems
import java.time.Clock
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Controller
class Controller(
    private val clock: Clock,
    private val properties: Properties,
    private val pages: Pages,
    private val redeems: Redeems
) {
    @GetMapping
    fun index(
        @RequestParam("dob", required = false) dateOfBirth: String?,
        @CookieValue("dob", required = false) cookie: String?,
        response: HttpServletResponse
    ): Any {
        checkRelease()
        return when {
            dateOfBirth == properties.dateOfBirth -> {
                val cook = Cookie("dob", dateOfBirth)
                cook.maxAge = 365 * 3600 //set expire time to 1000 sec
                response.addCookie(cook) //put cookie in response
                RedirectView(pages.getCurrentPage().id)
            }
            cookie == properties.dateOfBirth -> {
                RedirectView(pages.getCurrentPage().id)
            }
            else -> {
                "index"
            }
        }
    }

    @GetMapping("/minesweeper")
    fun minesweeper(
        @RequestParam("code", required = false) code: String?,
        @CookieValue("dob", required = false) cookie: String?
    ): Any {
        checkRelease()
        checkPage("minesweeper")
        if (cookie != properties.dateOfBirth) {
            return RedirectView("/")
        }
        return if (code == null || code != "boom") {
            "minesweeper"
        } else {
            unlockAndRedirect("location1")
        }
    }

    @GetMapping("/location1")
    fun location1(@CookieValue("dob", required = false) cookie: String?): Any {
        checkRelease()
        checkPage("location1")
        if (cookie != properties.dateOfBirth) {
            return RedirectView("/")
        }
        return "location1"
    }

    @GetMapping("/location2")
    fun location2(@CookieValue("dob", required = false) cookie: String?): Any {
        checkRelease()
        checkPage("location2")
        if (cookie != properties.dateOfBirth) {
            return RedirectView("/")
        }
        return "location2"
    }

    @GetMapping("/location3")
    fun location3(@CookieValue("dob", required = false) cookie: String?): Any {
        checkRelease()
        checkPage("location3")
        if (cookie != properties.dateOfBirth) {
            return RedirectView("/")
        }
        return "location3"
    }

    @GetMapping("/location4")
    fun location4(@CookieValue("dob", required = false) cookie: String?): Any {
        checkRelease()
        checkPage("location4")
        if (cookie != properties.dateOfBirth) {
            return RedirectView("/")
        }
        return "location4"
    }

    @GetMapping("/antgame")
    fun antgame(@CookieValue("dob", required = false) cookie: String?): Any {
        checkRelease()
        checkPage("antgame")
        if (cookie != properties.dateOfBirth) {
            return RedirectView("/")
        }
        return "antgame"
    }

    @GetMapping("/end")
    fun end(@CookieValue("dob", required = false) cookie: String?): Any {
        checkRelease()
        checkPage("end")
        if (cookie != properties.dateOfBirth) {
            return RedirectView("/")
        }
        return "end"
    }

    @GetMapping("/redeem")
    fun redeem(@RequestParam("code", required = false) code: String?): Any {
        checkRelease()
        if (code == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val redeemedPageId = redeems.redeem(code) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        return RedirectView(redeemedPageId)
    }


    private fun checkRelease() {
        if (clock.instant().isBefore(properties.release)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    private fun checkPage(pageId: String) {
        if (!pages.isUnlocked(pageId)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    private fun unlockAndRedirect(pageId: String): RedirectView {
        pages.unlock(pageId)
        return RedirectView(pageId)
    }
}