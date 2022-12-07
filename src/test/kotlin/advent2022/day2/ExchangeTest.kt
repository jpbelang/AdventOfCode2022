package advent2022.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ObjectsTest {

    class KnownMovesTest {

        @Test
        fun parse() {

            assertThat(KnownMoves.parse("A")).isEqualTo(KnownMoves.ROCK)
            assertThat(KnownMoves.parse("B")).isEqualTo(KnownMoves.PAPER)
            assertThat(KnownMoves.parse("C")).isEqualTo(KnownMoves.SCISORS)
            assertThat(KnownMoves.parse("X")).isEqualTo(KnownMoves.ROCK)
            assertThat(KnownMoves.parse("Y")).isEqualTo(KnownMoves.PAPER)
            assertThat(KnownMoves.parse("Z")).isEqualTo(KnownMoves.SCISORS)
        }
    }

    @Test
    fun scores() {

        assertThat(KnownMoves.ROCK.gamePointsVersus(KnownMoves.ROCK)).isEqualTo(3)
        assertThat(KnownMoves.ROCK.gamePointsVersus(KnownMoves.PAPER)).isEqualTo(0)
        assertThat(KnownMoves.ROCK.gamePointsVersus(KnownMoves.SCISORS)).isEqualTo(6)

        assertThat(KnownMoves.PAPER.gamePointsVersus(KnownMoves.ROCK)).isEqualTo(6)
        assertThat(KnownMoves.PAPER.gamePointsVersus(KnownMoves.PAPER)).isEqualTo(3)
        assertThat(KnownMoves.PAPER.gamePointsVersus(KnownMoves.SCISORS)).isEqualTo(0)

        assertThat(KnownMoves.SCISORS.gamePointsVersus(KnownMoves.ROCK)).isEqualTo(0)
        assertThat(KnownMoves.SCISORS.gamePointsVersus(KnownMoves.PAPER)).isEqualTo(6)
        assertThat(KnownMoves.SCISORS.gamePointsVersus(KnownMoves.SCISORS)).isEqualTo(3)

    }


    class ExchangeTest {

        @Test
        fun scores() {

            assertThat(Exchange(KnownMoves.ROCK, KnownMoves.PAPER).calculatePoints()).isEqualTo(8)
        }
    }
}