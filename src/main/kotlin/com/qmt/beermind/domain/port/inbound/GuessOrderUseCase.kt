package com.qmt.beermind.domain.port.inbound

import com.qmt.beermind.domain.model.Beer
import com.qmt.beermind.domain.model.BeerAnswer

interface GuessOrderUseCase {

    fun guessOrder(gameId: Int, userInput: List<Beer>): BeerAnswer

}