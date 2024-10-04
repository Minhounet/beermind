package com.qmt.beermind.domain.service

import com.qmt.beermind.domain.model.BeerGame
import com.qmt.beermind.domain.port.inbound.GetGamesInfoUseCase
import com.qmt.beermind.domain.port.outbound.BeerGameRepository

class GetGamesInfoService(private val beerGameRepository : BeerGameRepository) : GetGamesInfoUseCase {
    override fun getGames(): List<BeerGame> = beerGameRepository.getGames()
}