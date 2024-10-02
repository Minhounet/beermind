package com.qmt.beermind.domain.service

import com.qmt.beermind.domain.model.BeerGame
import com.qmt.beermind.domain.model.BeerGameState
import com.qmt.beermind.domain.model.GameLimitException
import com.qmt.beermind.domain.port.outbound.BeerGameRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class StartGameServiceTest {

    private lateinit var service: StartGameService
    private lateinit var beerGameRepository: BeerGameRepository

    @BeforeEach
    fun setUp() {
        beerGameRepository = mock(BeerGameRepository::class.java)
        service = StartGameService(beerGameRepository)
    }

    @Test
    fun SHOULD_THROW_GameLimitException_WHEN_starting_11_games() {
        val tenGames = listOf(
            BeerGame(0, listOf(), BeerGameState.RUNNING),
            BeerGame(1, listOf(), BeerGameState.RUNNING),
            BeerGame(2, listOf(), BeerGameState.RUNNING),
            BeerGame(3, listOf(), BeerGameState.RUNNING),
            BeerGame(4, listOf(), BeerGameState.RUNNING),
            BeerGame(5, listOf(), BeerGameState.RUNNING),
            BeerGame(6, listOf(), BeerGameState.RUNNING),
            BeerGame(7, listOf(), BeerGameState.RUNNING),
            BeerGame(8, listOf(), BeerGameState.RUNNING),
            BeerGame(9, listOf(), BeerGameState.RUNNING),
        )
        `when`(beerGameRepository.getRunningGames()).thenReturn(tenGames)
        assertThrows<GameLimitException> { service.startGame() }
    }

}