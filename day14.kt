import java.lang.Integer.max
import kotlin.collections.ArrayList

typealias int = Int
typealias list<T> = ArrayList<T>
typealias string = String

data class ivec2(val x: int, val y: int)

fun main() {
  val field = Array(400) { Array(200) { '0' } }
  var input: string
  val offsetX = 300
  var low = 0
  while (readlnOrNull().also { input = it ?: "" } != null) {
    val split = input.split(" -> ")
    val lis = list<ivec2>()
    for (it in split) {
      val split1 = it.split(",")
      lis.add(ivec2(split1[0].toInt(), split1[1].toInt()))
      low = max(low, split1[1].toInt())
    }

    for (i in 0 until lis.size - 1) {
      var start = lis[i]
      var end = lis[i + 1]
      if (start.y != end.y) {
        if (start.y > end.y) {
          val tmp = start
          start = end
          end = tmp
        }
        for (j in start.y..end.y) {
          field[start.x - offsetX][j] = '1'
        }
      } else {
        if (start.x > end.x) {
          val tmp = start
          start = end
          end = tmp
        }
        for (j in start.x..end.x) {
          field[j - offsetX][start.y] = '1'
        }
      }
    }
  }

  var num = 0
  outer@
  do {
    var sandX = 500 - offsetX
    var sandY = 0
    while (sandY < 199) {
      if (field[sandX][sandY + 1] == '0') {
        sandY++
        continue
      }
      if (field[sandX - 1][sandY + 1] == '0') {
        sandY++
        sandX--
        continue
      }
      if (field[sandX + 1][sandY + 1] == '0') {
        sandY++
        sandX++
        continue
      }
      field[sandX][sandY] = '2'
      num++
      continue@outer
    }
    break@outer
  } while (true)

  println(num)

  outer@
  do {
    var sandX = 500 - offsetX
    var sandY = 0
    while (sandY < low + 2) {
      if (field[sandX][sandY + 1] == '0' && sandY != low + 1) {
        sandY++
        continue
      }
      if (field[sandX - 1][sandY + 1] == '0' && sandY != low + 1) {
        sandY++
        sandX--
        continue
      }
      if (field[sandX + 1][sandY + 1] == '0' && sandY != low + 1) {
        sandY++
        sandX++
        continue
      }
      field[sandX][sandY] = '2'
      num++
      if (sandX == 500 - offsetX && sandY == 0)
        break@outer
      continue@outer
    }
    break@outer
  } while (true)

  println(num)
}
