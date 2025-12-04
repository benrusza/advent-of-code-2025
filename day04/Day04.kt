import java.io.File

fun main() {

    val rolls: List<String> = File("day04/input.txt").readLines(Charsets.UTF_8)
    
    paper(rolls)

}

fun paper(rolls: List<String>){
    var _rolls = rolls.toMutableList()
    


    var globalTotal = 0
    var lastTotal = 0

    do{

        val _rollstemp = _rolls

        var total = 0
        _rolls.forEachIndexed { index, lineroll ->
            lineroll.forEachIndexed { subindex, roll->
                var count = 0
                if(roll.toString()!="."){
                    
                    for(i in 0 until 3){
                        val nIndex = index-1 + i
                        if(nIndex>=0 && nIndex<_rolls.size){
                            for(x in 0 until 3){
                                val nsubIndex = subindex-1 + x
                                if(nsubIndex>=0 && nsubIndex<lineroll.length ){
                                    //println("index:$nIndex sub:$nsubIndex cont:${rolls[nIndex][nsubIndex]}")
                                    if(_rolls[nIndex][nsubIndex].toString()=="@"){
                                        if(!(x==1 && i==1)){
                                            count++
                                        }
                                    }
                                }
                            }
                        }    
                    }
                    if(count<4){
                        total++
                        var temp = _rollstemp[index].replaceRange(subindex, subindex+1, "x")
                        _rollstemp[index] = temp
                    }

                }
            
            }
        }
        _rolls = _rollstemp

        globalTotal = lastTotal
        lastTotal = total
        println("lastTotal:$lastTotal")
    }while(globalTotal != lastTotal)

    
    println("total:$globalTotal")


    /* 
    _rolls.forEach{
        println(it)
    }
    */
}
  
