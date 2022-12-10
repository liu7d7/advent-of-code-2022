using System.Text;

string[] file = File.ReadAllLines("in.txt");
long x = 1;
long c = 0;

Dictionary<long, int> ccToIdx = new Dictionary<long, int>
{
  { 20, 0 },
  { 60, 1 },
  { 100, 2 },
  { 140, 3 },
  { 180, 4 },
  { 220, 5 }
};

long[] s = new long[6];
List<List<char>> screen = new List<List<char>> { new List<char>() };
int scanLine = 0;

foreach (string line in file)
{
  string[] split = line.Split(" ");
  switch (split[0])
  {
    case "noop":
    {
      c++;
      afterCycle();
      break;
    }
    case "addx":
    {
      c++;
      afterCycle();
      c++;
      x += int.Parse(split[1]);
      afterCycle();
      break;
    }
  }
}

void afterCycle()
{
  if (c % 40 == 0) 
  {
    scanLine++;
    screen.Add(new List<char>());
  }
  screen[scanLine].Add(Math.Abs(c % 40 - x) <= 1 ? '█' : ' ');
  if (ccToIdx.TryGetValue(c + 1, out var idx)) s[idx] = x * (c + 1);
}

Console.WriteLine(s.Sum());
foreach (List<char> line in screen)
{
  StringBuilder sb = new();
  foreach (char ch in line)
  {
    sb.Append(ch);
  }
  Console.WriteLine(sb.ToString());
}