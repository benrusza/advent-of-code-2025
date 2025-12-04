import java.io.File

fun main() {

    val banks: List<String> = File("day03/input.txt").readLines(Charsets.UTF_8)
    var total = 0L

    for (bank in banks) {
        val maxJoltage = getMaxJoltage(bank)
        //println(maxJoltage)
        total += maxJoltage
    }
    println(total)
}

fun getMaxJoltage(bank: String, length: Int = 12): Long {
    var _bank = bank

    
    var joltage = ""

    for (i in 0 until length) {
        var max = 0
        var indexMax = 0
        _bank.forEachIndexed { index, n ->
            val number = n.toString().toInt()
            if (number > max && index < (_bank.length+1 - length + i)) {
                max = number
                indexMax = index
                if (max == 9) {
                    return@forEachIndexed
                }
            }else if(number > max && i == length-1){
                max = number
                indexMax = index
            }
        }
        joltage += max

        if(_bank.length>1){
            _bank = _bank.removeRange(0, indexMax + 1)
        }
    }
    println("bank:    "+bank)
    println("joltage: "+joltage)
    

    return joltage.toLong()
}
