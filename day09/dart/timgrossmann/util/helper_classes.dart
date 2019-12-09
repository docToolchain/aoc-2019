class Amplifier {
  int pointer = 0;
  int relativeBase = 0;
  CustomList instructions;

  Amplifier(CustomList this.instructions);

  @override
  String toString() {
    return '$pointer, $relativeBase';
  }
}

class CustomList {
  Map<int, int> instructions = Map<int, int>();

  CustomList(List<int> instructions) {
    instructions.asMap().forEach((int index, int value) =>
        this.instructions.putIfAbsent(index, () => value));
  }

  int operator [](int i) {
    if (!instructions.containsKey(i)) {
      instructions.putIfAbsent(i, () => 0);
      return 0;
    }

    return instructions[i];
  }

  void operator []=(int i, int value) =>
      instructions.update(i, (val) => value, ifAbsent: () => 0);
}