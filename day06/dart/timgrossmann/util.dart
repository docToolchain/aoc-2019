class Orbit {
  String name;
  int value;

  Orbit(this.name, this.value);

  @override
  String toString() {
    return name;
  }

  @override
  int get hashCode => super.hashCode;

  @override
  bool operator ==(other) {
    return this.name == other.name;
  }
}

String findStartPoint(List<List<String>> orbits) {
  List<String> parents = orbits.map((orbitPair) => orbitPair[0]).toList();

  List<String> childs = orbits.map((orbitPair) => orbitPair[1]).toList();

  String startPoint =
      parents.where((parent) => !childs.contains(parent)).toList().first;

  return startPoint;
}