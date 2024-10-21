package com.qmt.beermind.infrastructure.database

import com.qmt.beermind.domain.model.BeerGame
import java.util.Optional

interface BeerGameRepository {

    fun getGame(id : Int) : Optional<BeerGame>

    fun getRunningGames() : List<BeerGame>
}

