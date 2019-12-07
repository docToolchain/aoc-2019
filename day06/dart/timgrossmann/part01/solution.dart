import 'dart:io';

import '../util.dart';

int main(List<String> args) {
  File input = new File('./input.txt');
  List<List<String>> orbits = input
      .readAsStringSync()
      .split('\n')
      .map((line) => line.split(')'))
      .toList();

  Map<String, List<String>> mapping = Map<String, List<String>>();
  orbits.forEach((orbitPair) {
    String parent = orbitPair[0];
    String child = orbitPair[1];

    mapping.update(parent, (val) => [...val, child], ifAbsent: () => [child]);
  });

  String startPoint = findStartPoint(orbits);
  List<Orbit> nextElements = [Orbit(startPoint, 0)];
  int counter = 0;

  while (nextElements.length > 0) {
    Orbit currOrbit = nextElements.removeLast();
    counter += currOrbit.value;

    if (mapping[currOrbit.name] == null) {
      continue;
    }

    List<Orbit> nextOrbits = mapping[currOrbit.name]
        .map((orbit) => Orbit(orbit, currOrbit.value + 1))
        .toList();

    print('$currOrbit => $nextOrbits : ${currOrbit.value}');
    nextElements.addAll(nextOrbits);
  }

  print(counter);

  return 1;
}
