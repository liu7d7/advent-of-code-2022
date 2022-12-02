open System.IO

let rock0 = "A"
let paper0 = "B"
let scissors0 = "C"
let rock1 = "X"
let paper1 = "Y"
let scissors1 = "Z"

let mutable score0 = 0
let mutable score1 = 0
let main () =
  let lines = File.ReadAllLines "in.txt"
  for line in lines do
    let split = line.Split " "
    let a = split[0]
    let b = split[1]
    if a = rock0 then
      // lose
      if b = rock1 then
        score0 <- score0 + 3 + 1
        score1 <- score1 + 0 + 3
      // draw
      else if b = paper1 then
        score0 <- score0 + 6 + 2
        score1 <- score1 + 3 + 1
      // win
      else if b = scissors1 then
        score0 <- score0 + 0 + 3
        score1 <- score1 + 6 + 2
    else if a = paper0 then
      if b = rock1 then
        score0 <- score0 + 0 + 1
        score1 <- score1 + 0 + 1
      if b = paper1 then
        score0 <- score0 + 3 + 2
        score1 <- score1 + 3 + 2
      if b = scissors1 then
        score0 <- score0 + 6 + 3
        score1 <- score1 + 6 + 3
    else if a = scissors0 then
      if b = rock1 then
        score0 <- score0 + 6 + 1
        score1 <- score1 + 0 + 2
      if b = paper1 then
        score0 <- score0 + 0 + 2
        score1 <- score1 + 3 + 3
      if b = scissors1 then
        score0 <- score0 + 3 + 3
        score1 <- score1 + 6 + 1
    ()
  ()
  
main ()
printf $"{score0}\n"
printf $"{score1}\n"