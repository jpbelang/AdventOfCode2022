package advent2022.day2

fun main(args: Array<String>) {

   println(ExchangeParser.fromResource("/day2_input").asResponseSequence()
        .map{ it.calculatePoints() }
        .sum())

    println(ExchangeParser.fromResource("/day2_input").asDesiredResult()
        .map{ it.calculatePoints() }
        .sum())

}