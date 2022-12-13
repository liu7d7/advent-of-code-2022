#include <bits/stdc++.h>

using namespace std;

struct element {
  vector<shared_ptr<element>> children{};
  int val = -1;

  string to_string() {
    if (val != -1) {
      return std::to_string(val);
    }
    if (children.empty()) {
      return "[]";
    }
    stringstream ss;
    ss << '[';
    for (int i = 0; i < children.size(); i++) {
      ss << children[i]->to_string();
      if (i != children.size() - 1) ss << ',';
    }
    ss << ']';
    return ss.str();
  }
};

shared_ptr<element> parse(const string& in) {
  stack<shared_ptr<element>> es;
  shared_ptr<element> last;
  string cnum;
  for (char s : in) {
    if (s == ',' && !cnum.empty()) {
      auto child = make_shared<element>();
      child->val = strtol(cnum.c_str(), nullptr, 10);
      es.top()->children.push_back(child);
      cnum = "";
    } else if (s == '[') {
      auto e = make_shared<element>();
      if (!es.empty()) es.top()->children.push_back(e);
      es.push(e);
    } else if (s == ']') {
      if (!cnum.empty()) {
        auto child = make_shared<element>();
        child->val = strtol(cnum.c_str(), nullptr, 10);
        es.top()->children.push_back(child);
        cnum = "";
      }
      last = es.top();
      es.pop();
    } else if (isdigit(s)) {
      cnum += s;
    }
  }
  if (nullptr == last) {
    exit(-1);
  }
  return last;
}

template <typename T> int signum(T val) {
  return (T(0) < val) - (val < T(0));
}

// return 1 if good, 0 if neutral, -1 if bad
int comp(const shared_ptr<element>& lhs, const shared_ptr<element>& rhs, bool log = false) {
  // both are lists
  if (lhs->val == -1 && rhs->val == -1) {
    for (int i = 0; i < min(lhs->children.size(), rhs->children.size()); i++) {
      int ret = comp(lhs->children[i], rhs->children[i]);
      if (log)
        cout << ret << " because list comp 1 " << lhs->children[i]->to_string() << ' ' << rhs->children[i]->to_string() << '\n';
      if (ret != 0) return ret;
    }
    if (log)
      cout << signum((int)rhs->children.size() - (int)lhs->children.size()) << " because list comp 2 " << (int)lhs->children.size() << ' ' << (int)rhs->children.size() << '\n';
    return signum((int)rhs->children.size() - (int)lhs->children.size());
  }

  // both are ints
  if (lhs->val != -1 && rhs->val != -1) {
    if (log)
      cout << signum(rhs->val - lhs->val) << " because num comp 1 " << lhs->to_string() << ' ' << rhs->to_string() << '\n';
    return signum(rhs->val - lhs->val);
  }

  // rhs is list but lhs isn't
  if (lhs->val != -1) {
    auto lhsAsList = make_shared<element>();
    lhsAsList->children.push_back(lhs);
    int ret = comp(lhsAsList, rhs);
    if (log)
      cout << ret << " because mixed comp 1 " << lhs->to_string() << ' ' << rhs->to_string() << '\n';
    return ret;
  }

  // lhs is list but rhs isn't
  if (rhs->val != -1) {
    auto rhsAsList = make_shared<element>();
    rhsAsList->children.push_back(rhs);
    int ret = comp(lhs, rhsAsList);
    if (log)
      cout << ret << " because mixed comp 2 " << lhs->to_string() << ' ' << rhs->to_string() << '\n';
    return ret;
  }

  exit(-1);
}

int main() {
  int num{0};
  cout << signum(-1) << '\n';
  vector<shared_ptr<element>> es;
  {
    int i{1};
    string in, in2, in3;
    while (getline(cin, in)) {
      getline(cin, in2);
      getline(cin, in3);
      // in, in2 are the two lines
      auto a = parse(in);
      auto b = parse(in2);
      es.push_back(a);
      es.push_back(b);
      num += max(0, comp(a, b, false)) ? i : 0;
      if (in3 == "ok") break;
      i++;
    }
  }
  cout << num << '\n';

  // div packets
  {
    auto p2 = make_shared<element>();
    auto p2c = make_shared<element>();
    p2c->val = 2;
    p2->children.push_back(p2c);
    auto p6 = make_shared<element>();
    auto p6c = make_shared<element>();
    p6c->val = 6;
    p6->children.push_back(p6c);
    es.push_back(p2);
    es.push_back(p6);
  }

  std::sort(es.begin(), es.end(), [](const shared_ptr<element>& lhs, const shared_ptr<element>& rhs) {
    return max(0, comp(lhs, rhs));
  });

  int out{1};
  for (int i = 0; i < es.size(); i++) {
    if (es[i]->children.size() == 1) {
      if (es[i]->children[0]->val == 2 || es[i]->children[0]->val == 6) {
        out *= (i + 1);
      }
    }
  }
  cout << out << '\n';

  return 0;
}
