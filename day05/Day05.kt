import java.io.File

fun main() {

    val database: List<String> = File("day05/input.txt").readLines(Charsets.UTF_8)

    // part1(database)
    part22(database)
}

fun part1(database: List<String>) {

    var whiteSpaceId = 0
    var list = mutableListOf<Long>()

    database.forEachIndexed { index, it ->
        if (it.isEmpty()) {
            whiteSpaceId = index
            return@forEachIndexed
        }
    }

    for (i in whiteSpaceId + 1..database.size - 1) {
        if (database[i].isEmpty()) {
            println("error-")
        }
        for (j in 0..whiteSpaceId - 1) {
            // println(database[i] + " " + database[j] + " " + j)
            val ranges = database[j].split("-")
            val id = database[i].toLong()
            if (id >= ranges[0].toLong() && id <= ranges[1].toLong()) {
                list.add(id)

                break
            }
        }
    }

    println("fresh list:" + list.size)
}

fun part2(database: List<String>) {

    var ranges = ArrayList<Pair<Long, Long>>()

    database.forEach {
        if (!it.contains("-")) {
            return@forEach
        }

        val range1 = it.split("-")
        val temp = Pair(range1[0].toLong(), range1[1].toLong())
        var first = 0L
        var second = 0L

        mergeRanges(temp, ranges)
    }

    var lastsize = 0

    do {
        lastsize = ranges.size
        ranges.forEachIndexed { index, it -> mergeRanges(it, ranges) }
    } while (ranges.size != lastsize)

    var rangesClean = ArrayList<Pair<Long, Long>>()

    ranges.forEach {
        if (!rangesClean.contains(it)) {
            rangesClean.add(it)
        }
    }

    var count = 0L
    rangesClean.forEach {
        println(it)
        count += (it.second - it.first) + 1
    }

    println("fresh list:" + count)
}

fun mergeRanges(temp: Pair<Long, Long>, ranges: ArrayList<Pair<Long, Long>>) {

    var first = 0L
    var second = 0L
    var merged = false
    ranges.forEachIndexed { index, range ->
        if (temp.first >= range.first && temp.first <= range.second + 1) {
            first = range.first

            if (temp.second > range.second) {
                second = temp.second
            } else {
                second = range.second
            }

            ranges[index] = Pair(first, second)
            // println("1temp: $temp -> merged ${ranges[index]}")
            merged = true
            return@forEachIndexed
        } else if (temp.first <= range.first - 1 && temp.second >= range.first + 1) {
            first = temp.first
            if (temp.second > range.second) {
                second = temp.second
            } else {
                second = range.second
            }
            ranges[index] = Pair(first, second)
            // println("2temp: $temp -> merged ${ranges[index]}")
            merged = true
            return@forEachIndexed
        }
    }
    if (!merged) {
        ranges.add(temp)
        // println("not merged ${ranges} added temp: $temp")
    }
}

fun part22(database: List<String>) {

    var whiteSpaceId = 0

    database.forEachIndexed { index, it ->
        if (it.isEmpty()) {
            whiteSpaceId = index
            return@forEachIndexed
        }
    }
    val ranges = ArrayList<LongRange>()

    for (i in 0..whiteSpaceId-1) {
        val stringRange = database[i].split("-")
        ranges.add(LongRange(stringRange[0].toLong(), stringRange[1].toLong()))
    }
   

    val totalCount = calculateTotalCount(ranges)
    println("Total Fresh Ingredient IDs: $totalCount")
}


fun mergeRanges(ranges: List<LongRange>): List<LongRange> {
    val sortedRanges = ranges.sortedBy { it.first }
    val mergedRanges = mutableListOf<LongRange>()

    sortedRanges.forEach { range ->
        if (mergedRanges.isEmpty() || range.first > mergedRanges.last().last) {
            mergedRanges.add(range)
        } else {
            mergedRanges[mergedRanges.lastIndex] = (mergedRanges.last().first..maxOf(mergedRanges.last().last, range.last))
        }
    }

    return mergedRanges
}

fun calculateTotalCount(ranges: List<LongRange>): Long {
    return mergeRanges(ranges).sumOf { it.last - it.first + 1 }
}
