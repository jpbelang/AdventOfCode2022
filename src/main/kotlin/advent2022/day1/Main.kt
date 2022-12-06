package advent2022.day1

fun main(args: Array<String>) {

    val reader = CalorieReader.fromResource("/day1_input")
    println(reader.asSequence()
        .map{ it -> it.sum()}
        .sortedDescending().take(3).sum())

}