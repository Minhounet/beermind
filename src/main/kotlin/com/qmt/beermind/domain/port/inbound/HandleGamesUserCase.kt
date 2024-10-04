package com.qmt.beermind.domain.port.inbound

interface HandleGamesUserCase {

    fun startGame(): Int

    fun abortGame(gameId: Int)
}