#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> trees;

int main() {
  string s;
  int width, height;
  while (getline(cin, s)) {
    if (s == "ok") break;
    vector<int> v;
    for (char c : s) {
      v.push_back(c - '0');
    }
    trees.push_back(v);
  }
  width = trees[0].size();
  height = trees.size();
  int sum = height * 2 + width * 2 - 4;
  int sceneMax = 0;
  for (int i = 1; i < width - 1; i++) {
    for (int j = 1; j < height - 1; j++) {
      int treeHeight = trees[j][i];
      int t, b, l, r;
      t = b = l = r = true;
      int vt = j, vb = height - j - 1, vl = i, vr = width - i - 1;
      // from top
      for (int k = j - 1; k >= 0; k--) {
        if (trees[k][i] >= treeHeight) {
          vt = j - k;
          t = false;
          break;
        }
      }
      // from bottom
      for (int k = j + 1; k < height; k++) {
        if (trees[k][i] >= treeHeight) {
          vb = k - j;
          b = false;
          break;
        }
      }
      // from left
      for (int k = i - 1; k >= 0; k--) {
        if (trees[j][k] >= treeHeight) {
          vl = i - k;
          l = false;
          break;
        }
      }
      // from right
      for (int k = i + 1; k < width; k++) {
        if (trees[j][k] >= treeHeight) {
          vr = k - i;
          r = false;
          break;
        }
      }
      if (l || r || t || b) {
        sum++;
      }
      sceneMax = max(sceneMax, vl * vr * vt * vb);
      cout << i << ' ' << j << ' ' << vl << ' ' << vr << ' ' << vt << ' ' << vb << '\n';
    }
  }
  cout << sum << ' ' << sceneMax;
  return 0;
}
