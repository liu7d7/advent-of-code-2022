file = File.open "in.txt"
out1 = 0
out2 = 0
arr = file.readlines
for line in arr
  first = line[0..(line.length / 2 - 1)]
  out = ""
  for i in line.length / 2..line.length - 1
    for j in 0..first.length
      if line[i] == first[j]
        out = first[j]
        break
      end
    end
    break if out != ""
  end
  out1 += (out.ord >= 97 ? out.ord - 96 : out.ord - 65 + 27)
end
for i in (0..arr.length - 1).step 3
  i1 = arr[i]
  i2 = arr[i + 1]
  i3 = arr[i + 2]
  out = ""
  for i in 0..i1.length
    for j in 0..i2.length
      for k in 0..i3.length
        if i1[i] == i2[j] && i1[i] == i3[k]
          out = i1[i]
          break
        end
      end
      break if out != ""
    end
    break if out != ""
  end
  out2 += (out.ord >= 97 ? out.ord - 96 : out.ord - 65 + 27)
end
print "#{out1}\n#{out2}"