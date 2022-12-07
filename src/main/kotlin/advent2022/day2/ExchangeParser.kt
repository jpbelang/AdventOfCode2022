package advent2022.day2

import java.io.BufferedReader
import java.io.Reader

interface PointCalculator {
    fun calculatePoints(): Int
}

class Exchange(private val opponentMove: KnownMoves, private val myMove: KnownMoves) : PointCalculator {
    override fun calculatePoints(): Int {
        return myMove.gamePointsVersus(opponentMove) + myMove.shapePoints()
    }
}

class DesiredResult(private val opponentMove: KnownMoves, private val result: Outcomes): PointCalculator {
    override fun calculatePoints(): Int {
        val myMove = opponentMove.getFromDesiredResult(result)
        return myMove.gamePointsVersus(opponentMove) + myMove.shapePoints()
    }
}

enum class Outcomes {
    LOSS, DRAW, WIN;

    companion object {
        fun parse(name: String) = when (name) {
            "X" -> LOSS
            "Y" -> DRAW
            "Z" -> WIN
            else -> throw IllegalArgumentException("invalid name $name")

        }
    }
}

enum class KnownMoves{

    ROCK {
        override fun gamePointsVersus(opponentMov: KnownMoves): Int {

            return when (opponentMov) {
                ROCK -> 3
                PAPER -> 0
                SCISORS -> 6
            }
        }

        override fun shapePoints() = 1

        override fun getFromDesiredResult(result: Outcomes): KnownMoves = when (result) {
            Outcomes.LOSS -> SCISORS
            Outcomes.DRAW -> ROCK
            Outcomes.WIN -> PAPER
        }
    },
    PAPER {
        override fun gamePointsVersus(opponentMov: KnownMoves): Int {

            return when (opponentMov) {
                ROCK -> 6
                PAPER -> 3
                SCISORS -> 0
            }
        }

        override fun shapePoints() = 2

        override fun getFromDesiredResult(result: Outcomes): KnownMoves = when (result) {
            Outcomes.LOSS -> ROCK
            Outcomes.DRAW -> PAPER
            Outcomes.WIN -> SCISORS
        }

    },
    SCISORS {
        override fun gamePointsVersus(opponentMov: KnownMoves): Int {

            return when (opponentMov) {
                ROCK -> 0
                PAPER -> 6
                SCISORS -> 3
            }
        }

        override fun shapePoints() = 3

        override fun getFromDesiredResult(result: Outcomes): KnownMoves = when (result) {
            Outcomes.LOSS -> PAPER
            Outcomes.DRAW -> SCISORS
            Outcomes.WIN -> ROCK
        }

    };

    abstract fun gamePointsVersus(opponentMov: KnownMoves): Int
    abstract fun shapePoints(): Int
    abstract fun getFromDesiredResult(result: Outcomes): KnownMoves

    companion object {
        fun parse(name: String): KnownMoves = when (name) {
            "A" -> ROCK
            "X" -> ROCK
            "B" -> PAPER
            "Y" -> PAPER
            "C" -> SCISORS
            "Z" -> SCISORS
            else -> throw IllegalArgumentException("invalid name $name")
        }
    }
}

class ExchangeParser(private val reader: Reader) {

    companion object {
        fun fromResource(resource: String) =
            ExchangeParser(this::class.java.getResourceAsStream(resource)?.reader() ?: throw RuntimeException())
    }

    fun asResponseSequence(): Sequence<PointCalculator> {
        return BufferedReader(reader).lineSequence().map { asExchange(it) }
    }

    fun asDesiredResult(): Sequence<PointCalculator> {
        return BufferedReader(reader).lineSequence().map { asDesiredResult(it) }
    }

    private fun asExchange(it: String): Exchange {

        val letters = it.split(" ")
        return Exchange(KnownMoves.parse(letters[0]), KnownMoves.parse(letters[1]))
    }

    private fun asDesiredResult(it: String): DesiredResult {

        val letters = it.split(" ")
        return DesiredResult(KnownMoves.parse(letters[0]), Outcomes.parse(letters[1]))
    }

}