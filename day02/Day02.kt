import java.io.File

fun main(args: Array<String>) {

    val ids: List<String> = File("day02/input.txt").readLines(Charsets.UTF_8)[0].split(",")
    val invalidIds = arrayListOf<Long>()

    for (range in ids) {
        checkIdsRange2(range, invalidIds)
    }
    var sum = 0L
    for (id in invalidIds) {
        sum += id
    }
    println(sum)
}

fun checkIdsRange(idsRange: String, invalidIds: ArrayList<Long>) {
    val ranges = idsRange.split("-")

    for (i in ranges[0].toLong()..ranges[1].toLong()) {

        /** Part 1 */
        val id = i.toString()
        val len = id.length

        if (len % 2 == 0) {

            val id1 = id.substring(0, (len / 2))
            val id2 = id.substring((len / 2), len)
            if (id1 == id2) {
                invalidIds.add(i)
                println(i)
            }
        }
    }
}


fun checkIdsRange2(idsRange: String, invalidIds: ArrayList<Long>) {
    val ranges = idsRange.split("-")

    for (i in ranges[0].toLong()..ranges[1].toLong()) {
        val id = i.toString()
        
        for(x in 1..id.length/2){
            val temp = id.substring(0,x) 
            //println("$x = $temp :: ${id.substring(temp.length,temp.length+x)}")
            if(temp == id.substring(temp.length,temp.length+x)){
                if(checkPattern(id, temp)){

                    invalidIds.add(i)
                    println(i)
                    
                    break
                }
                
            }
            
        }

    }
}

fun checkPattern(original :String, pattern :String) : Boolean{
    original.split(pattern).forEach { 
        if(it.isNotEmpty()){
            return false
        }
    }
    return true
}