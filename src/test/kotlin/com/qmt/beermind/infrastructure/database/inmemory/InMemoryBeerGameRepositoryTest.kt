package com.qmt.beermind.infrastructure.database.inmemory

import com.qmt.beermind.domain.model.BeerGame
import com.qmt.beermind.domain.model.BeerGameState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class InMemoryBeerGameRepositoryTest {


    private var repository = InMemoryBeerGameRepository()

    @BeforeEach
    fun setUp() {
        repository = InMemoryBeerGameRepository().apply {
            objects.put(2, BeerGame(2, listOf(), BeerGameState.RUNNING, 0))
            objects.put(3, BeerGame(3, listOf(), BeerGameState.WIN, 0))
            objects.put(4, BeerGame(4, listOf(), BeerGameState.RUNNING, 0))
        }
    }

    @Test
    fun Should_return_optional_empty_When_object_does_not_exist() {
        assertEquals(Optional.empty<BeerGame>(), repository.getGame(1))
    }

    @Test
    fun Should_optional_with_beer_When_object_exist() {
        val beer = BeerGame(2, listOf(), BeerGameState.RUNNING, 0)
        assertEquals(Optional.of(beer), repository.getGame(2))
    }

    @Test
    fun Should_return_running_games() {
        val expected = listOf(
            BeerGame(2, listOf(), BeerGameState.RUNNING, 0),
            BeerGame(4, listOf(), BeerGameState.RUNNING, 0)
        ).sortedBy {
            it.gameId
        }
        assertEquals(expected, repository.getRunningGames().sortedBy { it.gameId })
    }
}