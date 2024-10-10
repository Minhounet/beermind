package com.qmt.beermind.domain.service

import com.qmt.beermind.domain.model.BeerGame
import com.qmt.beermind.domain.port.inbound.GetGamesInfoUseCase
import com.qmt.beermind.domain.port.outbound.BeerGamePort

class GetGamesInfoService(private val beerGamePort : BeerGamePort) : GetGamesInfoUseCase {
    override fun getGames(): List<BeerGame> = beerGamePort.getGames()
}