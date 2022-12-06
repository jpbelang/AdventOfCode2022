package advent2022.day1
import java.io.BufferedReader
import java.io.Reader

class CalorieReader(reader: Reader) {

    val iterator = BufferedReader(reader).lineSequence().iterator()

    companion object {
        fun fromResource(resource: String) = CalorieReader(this::class.java.getResourceAsStream("/day1_input")?.reader()?: throw RuntimeException())
    }

    fun asSequence(): Sequence<List<Int>> {
        return generateSequence { fetch() }
    }

    fun fetch(): List<Int>? {

        val list = ArrayList<Int>()
        while(iterator.hasNext()) {
            val line = iterator.next()
            if ( line.isEmpty() ) {
                break;
            }
            list.add(Integer.parseInt(line))
        }

        return if ( list.isEmpty() ) {
            null;
        } else {
            list;
        }
    }
}