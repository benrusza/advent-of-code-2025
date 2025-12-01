import java.io.File

fun main(args: Array<String>) {

    val movements: List<String> = File("day01/input.txt").readLines(Charsets.UTF_8)

    var currentPos = 50

    var password = 0

    for (movement in movements) {
        password += detectZero(movement, currentPos)

        currentPos = rotateDial(movement, currentPos)
        if (currentPos >= 100 || currentPos < 0) {
            println("Error en la posicion $currentPos")
            break
        }
        if (currentPos == 0) {
            // password++//part1
        }
    }

    println("La posicion es $currentPos y la contraseña es $password")
}
/*
 * Part 2
 */

fun detectZero(movement: String, currentPos: Int): Int {
    var count = 0
    var rotation = 0
    var _currentPos = currentPos
    if (movement.contains("L")) {
        rotation = Integer.parseInt(movement.replace("L", "")) * -1
    } else if (movement.contains("R")) {
        rotation = Integer.parseInt(movement.replace("R", ""))
    }
    _currentPos += rotation

    if (_currentPos == 0) {
        count++
    }

    if (_currentPos < 0) {
        _currentPos *= -1
        if (currentPos != 0) count++
    }

    count += _currentPos / 100

    println("Count es $count")
    return count
}

/*
 * Part 1
 */

fun rotateDial(movement: String, currentPos: Int): Int {
    var rotation = 0
    var _currentPos = currentPos
    if (movement.contains("L")) {
        rotation = Integer.parseInt(movement.replace("L", "")) * -1
    } else if (movement.contains("R")) {
        rotation = Integer.parseInt(movement.replace("R", ""))
    }
    _currentPos += rotation

    if (_currentPos < 0) {
        if (_currentPos <= -100) {
            _currentPos *= -1
            _currentPos = _currentPos % 100
            if (_currentPos == 0) {
                return _currentPos
            }
            _currentPos = 100 - _currentPos
        } else {
            _currentPos = 100 + _currentPos
        }
    } else if (_currentPos >= 100) {
        _currentPos = _currentPos % 100
    }

    println("$currentPos :: $rotation = $_currentPos")

    return _currentPos
}
