package com.qmt.beermind.domain.service

import com.qmt.beermind.domain.model.Beer
import com.qmt.beermind.domain.model.BeerAnswer
import com.qmt.beermind.domain.model.BeerGame
import com.qmt.beermind.domain.model.BeerGameState
import com.qmt.beermind.domain.port.outbound.BeerGamePort
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals

class GuessOrderServiceTest {

    private lateinit var service: GuessOrderService
    private lateinit var beerGamePort: BeerGamePort

    @BeforeEach
    fun setUp() {
        beerGamePort = mock(BeerGamePort::class.java)
        service = GuessOrderService(beerGamePort)
    }

    @Test
    fun Should_return_4_good_0_misplaced_When_order_is_correct() {
        `when`(beerGamePort.getGame(anyInt())).thenReturn(
            BeerGame(
                0,
                listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
                BeerGameState.RUNNING
            )
        )
        assertEquals(
            BeerAnswer(4, 0, BeerGameState.WIN),
            service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM))
        )
    }

    @Test
    fun Should_return_3_good_0_misplaced_When_order_is_correct() {
        `when`(beerGamePort.getGame(anyInt())).thenReturn(
            BeerGame(
                0,
                listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
                BeerGameState.RUNNING
            )
        )
        assertEquals(
            BeerAnswer(3, 0, BeerGameState.RUNNING),
            service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BREWDOG, Beer.DELIRIUM))
        )
    }

    @Test
    fun Should_return_2_good_2_misplaced_When_order_is_partially_correct() {
        `when`(beerGamePort.getGame(anyInt())).thenReturn(
            BeerGame(
                0,
                listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
                BeerGameState.RUNNING
            )
        )
        assertEquals(
            BeerAnswer(2, 2, BeerGameState.RUNNING),
            service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.DELIRIUM, Beer.BROOKLYN))
        )
    }

    @Test
    fun Should_return_1_good_2_misplaced_When_order_is_partially_correct() {
        `when`(beerGamePort.getGame(anyInt())).thenReturn(
            BeerGame(
                0,
                listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
                BeerGameState.RUNNING
            )
        )
        assertEquals(
            BeerAnswer(1, 2, BeerGameState.RUNNING),
            service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.DELIRIUM, Beer.BREWDOG, Beer.BROOKLYN))
        )
    }

    @Test
    fun Should_set_game_to_SUCCES_When_code_is_guessed() {
        `when`(beerGamePort.getGame(anyInt())).thenReturn(
            BeerGame(
                1,
                listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
                BeerGameState.RUNNING
            )
        )
        service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM))
        verify(beerGamePort, times(1)).markGameAsWon(1)
    }

    @Test
    fun Should_increment_attempts_For_each_guess() {
        `when`(beerGamePort.getGame(anyInt())).thenReturn(
            BeerGame(
                1,
                listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
                BeerGameState.RUNNING
            )
        )
        service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.DELIRIUM, Beer.DELIRIUM))
        verify(beerGamePort, times(1)).incrementAttempts(1)
    }

    @Test
    fun Should_set_game_to_lose_When_guess_is_wrong_and_attempts_is_reached() {
        val beerGame = BeerGame(
            1,
            listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
            BeerGameState.RUNNING
        )

        val spyBeer = spy(beerGame)
        `when`(beerGamePort.getGame(anyInt())).thenReturn(spyBeer)
        `when`(spyBeer.attempts).thenReturn(10)
        service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.DELIRIUM, Beer.DELIRIUM))
        verify(beerGamePort, times(1)).markGameAsLost(1)
    }

    @Test
    fun Should_has_lost_state_in_answer_When_guess_is_wrong_and_attempts_is_reached() {
        val beerGame = BeerGame(
            1,
            listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
            BeerGameState.RUNNING
        )

        val spyBeer = spy(beerGame)
        `when`(beerGamePort.getGame(anyInt())).thenReturn(spyBeer)
        `when`(spyBeer.attempts).thenReturn(10)

        val actual = service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.DELIRIUM, Beer.DELIRIUM))
        assertEquals(BeerGameState.LOSE, actual.gameState)
    }
}