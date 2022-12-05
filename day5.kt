fun main() {
    var str: String? = ""
    val firstChars = arrayOf(' ', '[')
    val field = arrayListOf<ArrayList<Char>>()
    while (firstChars.contains(readln().also { if (it.isNotEmpty()) str = it }.firstOrNull())) {
        val line = arrayListOf<Char>();
        for (i in 1..str!!.length step 4) {
            line.add(str!![i])
        }
        field.add(line)
    }
    val numStacks = str!![str!!.length - 2].digitToInt()
    val stacks = arrayListOf<ArrayList<Char>>()
    val stacksMover9000 = arrayListOf<ArrayList<Char>>()
    for (i in 1..numStacks) {
        stacks.add(ArrayList())
        stacksMover9000.add(ArrayList())
    }
    for (i in field.reversed()) {
        for ((idx, j) in i.withIndex()) {
            if (j.isLetter()) {
                stacks[idx].add(j)
                stacksMover9000[idx].add(j)
            }
        }
    }

    while (readlnOrNull().also { str = it } != null) {
        val split = str!!.split(" ");
        val num = split[1].toInt()
        val from = split[3].toInt() - 1
        val to = split[5].toInt() - 1

        stacks[to].addAll(stacks[from].slice(stacks[from].size - num until stacks[from].size).reversed())
        stacksMover9000[to].addAll(stacksMover9000[from].slice(stacksMover9000[from].size - num until stacksMover9000[from].size))
        for (i in 1..num) {
            stacks[from].removeLast()
            stacksMover9000[from].removeLast()
        }
        println(if (str!!.isEmpty()) "null" else str)
    }

    for (i in stacks) {
        print(i.last())
    }
    println()
    for (i in stacksMover9000) {
        print(i.last())
    }
}