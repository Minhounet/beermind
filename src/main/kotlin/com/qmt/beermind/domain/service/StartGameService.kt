package com.qmt.beermind.domain.service

import com.qmt.beermind.domain.model.GameLimitException
import com.qmt.beermind.domain.port.inbound.StartGameUseCase
import com.qmt.beermind.domain.port.outbound.BeerGameRepository

class StartGameService(val gameRepository: BeerGameRepository) : StartGameUseCase {

    override fun startGame(): Int {
        val existingGames = gameRepository.getRunningGames()
        if (existingGames.size == 10) {
            throw GameLimitException()
        }
        return gameRepository.createNewGame();
    }

}