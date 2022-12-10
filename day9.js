const fs = require('fs')

let input = fs.readFileSync('in.txt', 'utf-8')

{
  let headPos = {x: 0, y: 0}
  let tailPos = {x: 0, y: 0}
  let prevDiff = {x: 0, y: 0}
  let visited = new Set()
  let out = 0
  for (const line of input.split(/\r?\n/)) {
    let split = line.split(" ")
    for (let i = 0; i < parseInt(split[1], 10); i++) {
      switch (split[0]) {
        case "R":
          headPos.x++
          break;
        case "L":
          headPos.x--
          break;
        case "U":
          headPos.y--
          break;
        case "D":
          headPos.y++
          break;
      }

      if (Math.abs(headPos.x - tailPos.x) === 2 || Math.abs(headPos.y - tailPos.y) === 2) {
        tailPos.x += prevDiff.x
        tailPos.y += prevDiff.y
      }

      if (!visited.has(JSON.stringify(tailPos))) {
        visited.add(JSON.stringify({x: tailPos.x, y: tailPos.y}))
        out++
      }

      prevDiff = {x: headPos.x - tailPos.x, y: headPos.y - tailPos.y}
    }
  }

  console.log(out)
}

{
  let out = 0
  let poses = []
  let diffs = []
  let visited = new Set()
  for (let i = 0; i < 10; i++) {
    poses.push({x: 0, y: 0})
    diffs.push({x: 0, y: 0})
  }
  let iter = 0
  for (const line of input.split(/\r?\n/)) {
    let split = line.split(" ")
    let motion = { x: 0, y: 0 }
    switch (split[0]) {
      case "R": motion.x++; break;
      case "L": motion.x--; break;
      case "U": motion.y--; break;
      case "D": motion.y++; break;
    }

    for (let i = 0; i < parseInt(split[1], 10); i++) {
      poses[0].x += motion.x;
      poses[0].y += motion.y;
      for (let j = 1; j < 10; j++) {
        let headPos = poses[j - 1]
        let tailPos = poses[j]
        if (Math.abs(headPos.x - tailPos.x) === 1 && Math.abs(headPos.y - tailPos.y) === 2) {
          tailPos.x += Math.sign(headPos.x - tailPos.x)
          tailPos.y += Math.sign(headPos.y - tailPos.y)
          continue
        }
        if (Math.abs(headPos.x - tailPos.x) === 2 && Math.abs(headPos.y - tailPos.y) === 1) {
          tailPos.x += Math.sign(headPos.x - tailPos.x)
          tailPos.y += Math.sign(headPos.y - tailPos.y)
          continue
        }
        if (Math.abs(headPos.x - tailPos.x) === 2) {
          tailPos.x += Math.sign(headPos.x - tailPos.x)
        }
        if (Math.abs(headPos.y - tailPos.y) === 2) {
          tailPos.y += Math.sign(headPos.y - tailPos.y)
        }
      }
      if (!visited.has(JSON.stringify(poses[9]))) {
        visited.add(JSON.stringify(poses[9]))
        out++
      }
      iter++
    }
  }

  console.log(out)
}

