package com.qmt.beermind.infrastructure.database.inmemory

import com.qmt.beermind.domain.model.BeerGame
import com.qmt.beermind.domain.port.outbound.BeerGamePort
import com.qmt.beermind.infrastructure.database.BeerGameRepository
import org.springframework.stereotype.Component

@Component
class BeerGameInMemoryAdapter(private val beerGameRepository: BeerGameRepository) : BeerGamePort {

    override fun getGame(id: Int): BeerGame {
        TODO("Not yet implemented")
    }

    override fun getRunningGames(): List<BeerGame> {
        TODO("Not yet implemented")
    }

    override fun createNewGame(): Int {
        TODO("Not yet implemented")
    }

    override fun getGames(): List<BeerGame> {
        TODO("Not yet implemented")
    }

    override fun markGameAsWon(id: Int) {
        TODO("Not yet implemented")
    }

    override fun markGameAsAborted(id: Int) {
        TODO("Not yet implemented")
    }

    override fun incrementAttempts(id: Int) {
        TODO("Not yet implemented")
    }

    override fun markGameAsLost(gameId: Int) {
        TODO("Not yet implemented")
    }
}