import java.lang.IllegalStateException

typealias list<T> = ArrayList<T>

data class monkey(val items: list<Long>, var op: Char, var by: Int, var test: Int, var ifTrue: Int, var ifFalse: Int, var inspections: Long = 0)

var mod: Int = 1

fun iter(monkeys: list<monkey>, div: Boolean = true) {
  for (monkey in monkeys) {
    for (item in monkey.items) {
      monkey.inspections++
      var value = when (monkey.op) {
        '+' -> item + monkey.by
        '-' -> item - monkey.by
        '*' -> item * if (monkey.by == -1) item else monkey.by.toLong()
        else -> throw IllegalStateException(monkey.op.toString())
      }
      if (div) {
        value /= 3L
      } else {
        value %= mod
      }
      if (value % monkey.test == 0L) {
        monkeys[monkey.ifTrue].items.add(value)
      } else {
        monkeys[monkey.ifFalse].items.add(value)
      }
    }
    monkey.items.clear()
  }
  print(".")
}

fun main() {
  val monkeys: list<monkey> = ArrayList()
  var line: String
  while (true) {
    val current = monkey(list(), '_', 0, 0, 0, 0);
    readln()
    line = readln()
    line = line.substring(18)
    current.items.addAll(line.split(", ").map { it.toLong() })
    line = readln()
    current.op = line.split(" ")[6][0]
    current.by = line.split(" ").last().toIntOrNull() ?: -1
    line = readln()
    current.test = line.split(" ").last().toInt()
    line = readln()
    current.ifTrue = line.split(" ").last().toInt()
    line = readln()
    current.ifFalse = line.split(" ").last().toInt()
    monkeys.add(current)
    if (readlnOrNull() == null) {
      break
    }
  }

  for (monkey in monkeys) {
    mod *= monkey.test
  }

  var new = list<monkey>()
  for (monkey in monkeys) {
    new.add(monkey.copy(items = list<Long>().also { it.addAll(monkey.items) }))
  }
  for (i in 1..20) {
    iter(new)
  }
  new.sortByDescending { it.inspections }
  println(new[0].inspections * new[1].inspections)

  new = list()
  for (monkey in monkeys) {
    new.add(monkey.copy(items = list<Long>().also { it.addAll(monkey.items) }))
  }
  for (i in 1..10000) {
    iter(new, false)
  }
  new.sortByDescending { it.inspections }
  println(new[0].inspections * new[1].inspections)
}
