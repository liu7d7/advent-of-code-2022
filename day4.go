package main

import (
   "bufio"
   "fmt"
   "os"
   "strconv"
   "strings"
)

type (
   Pair struct {
      a, b int
   }
)

func main() {
   file, err := os.Open("in.txt")
   if err != nil {
      panic("hi")
   }

   in := bufio.NewScanner(file)
   in.Split(bufio.ScanLines)

   lines := make([]string, 0)
   for in.Scan() {
      lines = append(lines, in.Text())
   }

   out1 := 0
   out2 := 0

   pairs := make([]Pair, 0)

   for _, line := range lines {
      split := strings.Split(line, ",")
      p1 := strings.Split(split[0], "-")
      p2 := strings.Split(split[1], "-")
      i11, _ := strconv.Atoi(p1[0])
      i12, _ := strconv.Atoi(p1[1])
      i21, _ := strconv.Atoi(p2[0])
      i22, _ := strconv.Atoi(p2[1])
      pairs = append(pairs, Pair{i11, i12}, Pair{i21, i22})
   }

   for i := 0; i < len(pairs); i += 2 {
      i11 := pairs[i].a
      i12 := pairs[i].b
      i21 := pairs[i+1].a
      i22 := pairs[i+1].b
      if (i11 <= i21 && i12 >= i22) || (i21 <= i11 && i22 >= i12) {
         fmt.Println(i11, i12, i21, i22)
         out1++
         out2++
         continue
      }
      if (i12 >= i21 && i11 < i21) || (i22 >= i11 && i21 < i11) {
         fmt.Println("-", i11, i12, i21, i22)
         out2++
      }
   }

   fmt.Println(out1)
   fmt.Println(out2)
}
