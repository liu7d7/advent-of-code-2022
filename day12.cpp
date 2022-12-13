#include <bits/stdc++.h>

using namespace std;

int main() {

  vector<string> field;
  int sx, sy;
  int width, height;
  {
    string in;
    int y{0};
    getline(cin, in);
    while (in != "ok") {
      for (int i = 0; i < in.length(); i++) {
        if (in[i] == 'E') in[i] = '{';
        else if (in[i] == 'S') {
          sx = i;
          sy = y;
          in[i] = 'a';
        }
      }
      field.push_back(in);
      y++;
      getline(cin, in);
    }
    width = field[0].length();
    height = field.size();
  }

  struct head {
    int x, y;
    int len;
  };

  int* dp = new int[width * height];
  fill(dp, dp + width * height, INT32_MAX);

  const pair<int, int> neighbors[4] = {
      make_pair(0, 1),
      make_pair(0, -1),
      make_pair(1, 0),
      make_pair(-1, 0)
  };

  auto in_bounds = [width, height](int x, int y) {
    return x >= 0 && x < width && y >= 0 && y < height;
  };

  queue<head> q;

  {
    q.push(head{sx, sy, 0});
    dp[sx + sy * width] = 0;
    // uncomment following lines in block for part 2
    for (int y = 0; y < field.size(); y++) {
      for (int x = 0; x < field[y].size(); x++) {
        if (field[y][x] == 'a' && !(x == sx && y == sy)) {
          q.push(head{x, y, 0});
          dp[x + y * width] = 0;
        }
      }
    }
  }

  int min = INT32_MAX;
  while (!q.empty()) {
    head f = q.front();
    q.pop();

    if (field[f.y][f.x] == '{') {
      min = std::min(f.len, min);
      continue;
    }

    for (const auto& neighbor : neighbors) {
      int nx = f.x + neighbor.first;
      int ny = f.y + neighbor.second;
      bool in = in_bounds(nx, ny);
      if (!in) continue;
      if ((field[ny][nx] - field[f.y][f.x]) <= 1 && dp[nx + ny * width] > f.len + 1) {
        q.push(head{nx, ny, f.len + 1});
        dp[nx + ny * width] = f.len + 1;
      }
    }
  }
  cout << min;

  return 0;
}
