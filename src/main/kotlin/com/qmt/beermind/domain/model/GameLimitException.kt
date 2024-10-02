package com.qmt.beermind.domain.model

import java.lang.Exception

class GameLimitException() : Exception("Number of game limit exceeded (10)") {
}