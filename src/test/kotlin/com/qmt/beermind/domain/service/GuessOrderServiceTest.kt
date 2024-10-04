package com.qmt.beermind.domain.service

import com.qmt.beermind.domain.model.Beer
import com.qmt.beermind.domain.model.BeerAnswer
import com.qmt.beermind.domain.model.BeerGame
import com.qmt.beermind.domain.model.BeerGameState
import com.qmt.beermind.domain.port.outbound.BeerGameRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals

class GuessOrderServiceTest {

    private lateinit var service: GuessOrderService
    private lateinit var beerGameRepository: BeerGameRepository

    @BeforeEach
    fun setUp() {
        beerGameRepository = mock(BeerGameRepository::class.java)
        service = GuessOrderService(beerGameRepository)
    }

    @Test
    fun Should_return_4_good_0_misplaced_When_order_is_correct() {
        `when`(beerGameRepository.getGame(anyInt())).thenReturn(
            BeerGame(
                0,
                listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
                BeerGameState.RUNNING
            )
        )
        assertEquals(
            BeerAnswer(4, 0),
            service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM))
        )
    }

    @Test
    fun Should_return_3_good_0_misplaced_When_order_is_correct() {
        `when`(beerGameRepository.getGame(anyInt())).thenReturn(
            BeerGame(
                0,
                listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
                BeerGameState.RUNNING
            )
        )
        assertEquals(
            BeerAnswer(3, 0),
            service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BREWDOG, Beer.DELIRIUM))
        )
    }

    @Test
    fun Should_return_2_good_2_misplaced_When_order_is_partially_correct() {
        `when`(beerGameRepository.getGame(anyInt())).thenReturn(
            BeerGame(
                0,
                listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
                BeerGameState.RUNNING
            )
        )
        assertEquals(
            BeerAnswer(2, 2),
            service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.DELIRIUM, Beer.BROOKLYN))
        )
    }

    @Test
    fun Should_return_1_good_2_misplaced_When_order_is_partially_correct() {
        `when`(beerGameRepository.getGame(anyInt())).thenReturn(
            BeerGame(
                0,
                listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
                BeerGameState.RUNNING
            )
        )
        assertEquals(
            BeerAnswer(1, 2),
            service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.DELIRIUM, Beer.BREWDOG, Beer.BROOKLYN))
        )
    }

    @Test
    fun Should_set_game_to_SUCCES_When_code_is_guessed() {
        `when`(beerGameRepository.getGame(anyInt())).thenReturn(
            BeerGame(
                1,
                listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM),
                BeerGameState.RUNNING
            )
        )
        service.guessOrder(1, listOf(Beer.CHOUFFE, Beer.CHOUFFE, Beer.BROOKLYN, Beer.DELIRIUM))
        verify(beerGameRepository, times(1)).markGameAsWon(1)
    }
}