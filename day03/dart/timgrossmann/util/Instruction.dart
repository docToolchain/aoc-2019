enum Direction {
  none,
  LEFT,
  RIGHT,
  UP,
  DOWN
}

class Instruction {
  Direction dir;
  int amount;

  Instruction (Direction dir, int amount) {
    this.dir = dir;
    this.amount = amount;
  }

  String toString() {
    return '$dir - $amount';
  }
}