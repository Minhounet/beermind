package com.qmt.beermind.domain.port.inbound

import com.qmt.beermind.domain.model.BeerGame

interface GetGamesInfoUseCase {

    fun getGames(): List<BeerGame>


}