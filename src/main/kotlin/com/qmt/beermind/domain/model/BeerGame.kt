package com.qmt.beermind.domain.model

data class BeerGame(val gameId: Int, val beers: List<Beer>, val state: BeerGameState, val attempts: Int = 0)
