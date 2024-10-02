package com.qmt.beermind.domain.port.outbound

import com.qmt.beermind.domain.model.BeerGame

interface BeerGameRepository {

    fun getGame(id: Int): BeerGame
}