import 'dart:io';

import 'util/classes/Asteroid.dart';
import 'util/classes/Laser.dart';
import 'util/util.dart';

Asteroid getBestSightAsteroid(List<Asteroid> asteroids) {
  Asteroid bestAsteroid = asteroids[0];

  for (Asteroid asteroid in asteroids) {
    int numOfDetectedAsteroids =
        getAsteroidsInSight(asteroids, asteroid).length;

    if (numOfDetectedAsteroids > bestAsteroid.numOfVisibleAsteroids) {
      bestAsteroid = asteroid;
    }
  }

  return bestAsteroid;
}

Asteroid getAsteroidEvaporatedAt(
    List<Asteroid> asteroids, Asteroid bestBase, int evaporatedAt) {
  Set<Asteroid> toBeEvaporated = Set<Asteroid>.of(asteroids)
      .where((Asteroid asteroid) => asteroid != bestBase)
      .toSet();

  // start pointing up
  Laser laser = Laser();
  int evaporated = 0;

  while (true) {
    Map<Asteroid, double> asteroidsInDirection =
        getasteroidsInDirection(toBeEvaporated, bestBase, laser.direction);

    if (asteroidsInDirection.length > 0) {
      // print(asteroidsInDirection);

      Asteroid closestInDirection = asteroidsInDirection.keys.toList()[0];

      asteroidsInDirection.forEach((Asteroid asteroid, double distance) => {
            if (distance < asteroidsInDirection[closestInDirection])
              {closestInDirection = asteroid}
          });

      // if we reached the requested asteroid number
      if (evaporated == (evaporatedAt - 1)) {
        print('Found Asterioid for position $evaporatedAt: $closestInDirection');
        return closestInDirection;
      }

      // print('Evaporating: $closestInDirection');
      toBeEvaporated.remove(closestInDirection);
      evaporated++;
    }

    // move the laser
    laser.roateStep();
  }
}

int main(List<String> args) {
  File input = new File('./input.txt');

  List<List<String>> asteroidsMap = input
      .readAsStringSync()
      .split("\n")
      .map((String line) => line.split(''))
      .toList();

  List<Asteroid> asteroids = [];

  for (int i = 0; i < asteroidsMap.length; i++) {
    for (int j = 0; j < asteroidsMap[i].length; j++) {
      if (asteroidsMap[i][j] == '#') {
        asteroids.add(Asteroid(i, j));
      }
    }
  }

  printAsGrid(asteroidsMap);
  
  Asteroid bestBase = getBestSightAsteroid(asteroids);
  print('Best Base $bestBase');

  Asteroid evaporate200 = getAsteroidEvaporatedAt(asteroids, bestBase, 200);
  // print('Asteroid at 200: $evaporate200');
  print('Asteroid value: ${evaporate200.y * 100 + evaporate200.x}');

  return 1;
}