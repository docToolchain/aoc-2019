import 'dart:io';

import '../util.dart';

int main(List<String> args) {
  File input = new File('../input.txt');
  List<List<String>> orbits = input
      .readAsStringSync()
      .split('\n')
      .map((line) => line.split(')'))
      .toList();

  // link each orbit to its childs
  Map<String, List<String>> mapping = Map<String, List<String>>();
  orbits.forEach((orbitPair) {
    String parent = orbitPair[0];
    String child = orbitPair[1];

    mapping.update(parent, (val) => [...val, child], ifAbsent: () => [child]);
  });

  // create double linking
  Map<String, List<String>> doubleLinkedMapping = Map.of(mapping);
  mapping.forEach((parent, childs) {
    childs.forEach((child) => {
      doubleLinkedMapping.update(child, (val) => [...val, parent], ifAbsent: () => [parent])
    });
  });

  // track next and already visited elements
  List<Orbit> nextElements = [Orbit('YOU', 0)];
  List<Orbit> visitedElements = [Orbit('YOU', 0)];

  while (nextElements.length > 0) {
    Orbit currOrbit = nextElements.removeLast();
    visitedElements.add(currOrbit);

    if (currOrbit.name == 'SAN') {
      // - 2 because we don't count YOU and SAN
      print(currOrbit.value - 2);
      break;
    }

    if (doubleLinkedMapping[currOrbit.name] == null) {
      continue;
    }

    List<Orbit> nextOrbits = doubleLinkedMapping[currOrbit.name]
        .map((orbit) => Orbit(orbit, currOrbit.value + 1))
        .toList();

    print('$currOrbit => $nextOrbits : ${currOrbit.value}');
    nextOrbits.forEach((orbit) {
      if (!nextElements.contains(orbit) && !visitedElements.contains(orbit)) {
        nextElements.add(orbit);
      }
    });
  }

  return 1;
}
