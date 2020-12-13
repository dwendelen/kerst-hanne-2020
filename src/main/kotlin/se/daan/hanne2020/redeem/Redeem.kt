package se.daan.hanne2020.redeem

import org.springframework.data.annotation.Id
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import se.daan.hanne2020.page.Pages

@Component
class Redeem(
    private val repo: RedeemCodeRepository,
    private val page: Pages
) {
    fun redeem(code: String): String? {
        val maybe = repo.findById(code)
        if (maybe.isEmpty) {
            return null
        }
        val redeemCode = maybe.get()
        return if (page.isUnlocked(redeemCode.from)) {
            page.unlock(redeemCode.to)
            redeemCode.to
        } else {
            null
        }
    }
}

data class RedeemCode(
    @Id
    val code: String,
    val from: String,
    val to: String
)

interface RedeemCodeRepository : CrudRepository<RedeemCode, String>