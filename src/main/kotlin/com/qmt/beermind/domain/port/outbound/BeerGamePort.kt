package com.qmt.beermind.domain.port.outbound

import com.qmt.beermind.domain.model.BeerGame

interface BeerGamePort {

    fun getGame(id: Int): BeerGame

    fun getRunningGames() : List<BeerGame>
    fun createNewGame(): Int
    fun getGames(): List<BeerGame>
    fun markGameAsWon(id: Int)
    fun markGameAsAborted(id: Int)
    fun incrementAttempts(id: Int)
    fun markGameAsLost(gameId: Int)
}