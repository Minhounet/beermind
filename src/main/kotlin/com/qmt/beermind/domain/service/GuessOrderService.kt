package com.qmt.beermind.domain.service

import com.qmt.beermind.domain.model.Beer
import com.qmt.beermind.domain.model.BeerAnswer
import com.qmt.beermind.domain.model.BeerGameState
import com.qmt.beermind.domain.port.inbound.GuessOrderUseCase
import com.qmt.beermind.domain.port.outbound.BeerGameRepository

class GuessOrderService(private val beerGameRepository: BeerGameRepository) : GuessOrderUseCase {

    override fun guessOrder(gameId: Int, userInput: List<Beer>): BeerAnswer {
        beerGameRepository.incrementAttempts(gameId)
        val solution = beerGameRepository.getGame(gameId).beers
        val indexesGuessedItems = getIndexesGuessedItems(userInput, solution)

        val nbInGoodPlace = indexesGuessedItems.count { it }
        if (nbInGoodPlace == solution.size) {
            beerGameRepository.markGameAsWon(gameId)
            return BeerAnswer(nbInGoodPlace, 0, BeerGameState.WIN)
        }

        val nonGuessedItems = solution.filterIndexed { index, _ -> !indexesGuessedItems[index] }
        val misplacedCandidates = userInput.filterIndexed { index, _ -> !indexesGuessedItems[index] }
        val misPlaced = misplacedCandidates.count { nonGuessedItems.contains(it) }

        if (10 == beerGameRepository.getGame(gameId).attempts) {
            beerGameRepository.markGameAsLost(gameId)
            return BeerAnswer(nbInGoodPlace, misPlaced, BeerGameState.LOSE)
        } else {
            return BeerAnswer(nbInGoodPlace, misPlaced, BeerGameState.RUNNING)
        }
    }

    private fun getIndexesGuessedItems(orders: List<Beer>, beers: List<Beer>): List<Boolean> {
        require(orders.size == beers.size) { "must be same size" }
        return orders.mapIndexed { index, beer -> beer == beers[index] }
    }

}