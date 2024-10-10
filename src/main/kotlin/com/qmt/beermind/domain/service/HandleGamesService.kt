package com.qmt.beermind.domain.service

import com.qmt.beermind.domain.model.GameLimitException
import com.qmt.beermind.domain.port.inbound.HandleGamesUserCase
import com.qmt.beermind.domain.port.outbound.BeerGamePort

class HandleGamesService(private val gameRepository: BeerGamePort) : HandleGamesUserCase {

    override fun startGame(): Int {
        val existingGames = gameRepository.getRunningGames()
        if (existingGames.size == 10) {
            throw GameLimitException()
        }
        return gameRepository.createNewGame();
    }

    override fun abortGame(gameId: Int) {
        gameRepository.markGameAsAborted(gameId)
    }

}