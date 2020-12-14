package se.daan.hanne2020.page

import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Instant

@Component
class Pages(
    private val repo: PageRepository,
    private val clock: Clock
) {
    fun isUnlocked(pageId: String): Boolean {
        val page = repo.findById(pageId).orElseThrow { IllegalStateException("Unknown page $pageId") }
        return page.unlocked
    }

    fun unlock(pageId: String) {
        val page = repo.findById(pageId).orElseThrow { IllegalStateException("Unknown page $pageId") }
        unlock(page)
    }

    private fun unlock(page: Page) {
        if (page.unlocked) {
            return
        }
        val newPage = page.copy(unlocked = true, unlockedOn = clock.instant())
        repo.save(newPage)
    }

    fun getCurrentPage(): Page {
        val page = repo.findCurrentPage()
        return if(page == null) {
            val firstPage = repo.findFirstPage()
            unlock(firstPage)
            firstPage
        } else {
             page
        }
    }
}

data class Page(
    @Id
    val id: String,
    val unlocked: Boolean,
    val unlockedOn: Instant?,
    val order: Int
)

interface PageRepository : CrudRepository<Page, String> {
    @Query("SELECT * FROM PAGE WHERE UNLOCKED = TRUE ORDER BY \"ORDER\" DESC LIMIT 1")
    fun findCurrentPage(): Page?

    @Query("SELECT * FROM PAGE ORDER BY \"ORDER\" LIMIT 1")
    fun findFirstPage(): Page
}