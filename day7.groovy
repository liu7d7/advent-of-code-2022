class Main {
  static String join(Stack<String> s) {
    StringBuilder sb = new StringBuilder()
    for (String s1 : s) {
      sb.append(s1.substring(s1.lastIndexOf("/") + 1))
      sb.append("/")
    }
    return sb.toString()
  }

  static void main(String[] args) {
    final Scanner sc = new Scanner(System.in)
    ArrayList<String> commands = new ArrayList<>()
    while (true) {
      try {
        String line = sc.nextLine()
        commands.add(line)
      } catch (Exception ignored) {
        break
      }
    }
    HashMap<String, Long> sizes = new HashMap<>()
    HashMap<String, Set<String>> added = new HashMap<>()
    sizes["/0"] = 0l
    added["/0"] = new HashSet<String>()
    long depth = 0
    Stack<String> dirs = new Stack<>()
    for (String s : commands) {
      String[] split = s.split(" ")
      if (split[0] == "\$") {
        if (split[1] == "cd") {
          if (split[2] == "..") {
            depth--
            dirs.pop()
          } else if (split[2] == "/") {
            depth = 0
            dirs.clear()
            dirs.add("/0")
          } else {
            depth++
            dirs.add("${join(dirs)}/${split[2]}")
          }
        }
      } else if (split[0] == "dir") {
        if (!sizes.containsKey("${join(dirs)}/${split[1]}")) {
          sizes["${join(dirs)}/${split[1]}"] = 0l
          added["${join(dirs)}/${split[1]}"] = new HashSet<String>()
        }
      } else {
        for (String s1 : dirs) {
          if (added[s1].add("${join(dirs)}/${split[1]}")) {
            sizes[s1] = sizes[s1] + split[0].toLong()
          }
        }
      }
    }
    long sum = 0
    long needed = 30_000_000 - (70_000_000 - sizes["/0"])
    String min = "/0"
    for (String i : sizes.keySet()) {
      println("$i=${sizes[i]}")
      if (sizes[i] <= 100_000) {
        sum += sizes[i]
      }
      if (sizes[i] < sizes[min] && sizes[i] > needed) {
        min = i;
      }
    }
    println(sum)
    println(sizes[min])
  }
}
