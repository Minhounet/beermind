package com.qmt.beermind.infrastructure.database.inmemory

import com.qmt.beermind.domain.model.BeerGame
import com.qmt.beermind.domain.model.BeerGameState
import com.qmt.beermind.infrastructure.database.BeerGameRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class InMemoryBeerGameRepository : BeerGameRepository {

    val objects : MutableMap<Int, BeerGame> = Collections.synchronizedMap(HashMap())

    override fun getGame(id: Int): Optional<BeerGame> {
        return Optional.ofNullable(objects[id])
    }

    override fun getRunningGames(): List<BeerGame> {
        return objects
            .filter { it.value.state == BeerGameState.RUNNING }
            .toList()
            .map { it.second }
    }
}