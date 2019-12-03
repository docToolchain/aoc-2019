class WirePoint {
  int x;
  int y;
  int wireNum;
  int steps;

  WirePoint (int x, int y, int wireNum, [int steps = 0]) {
    this.x = x;
    this.y = y;
    this.wireNum = wireNum;
    this.steps = steps;
  }

  // hashCode and operator == needed for contains check
  int get hashCode {
    return x * 1000000 + y;
  }

  // override == operator to be true if x and y are equal
  bool operator == (o) {
    return ((this.x == o.x) && (this.y == o.y));
  }

  String toString () {
    return '$x, $y, $wireNum, $steps';
  }
}