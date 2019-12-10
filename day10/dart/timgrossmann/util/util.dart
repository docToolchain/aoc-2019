import 'dart:math';

import 'classes/Asteroid.dart';
import 'classes/Vector2.dart';

void printAsGrid(List<List<String>> grid) {
  for (List<String> row in grid) {
    print(row);
  }
}

double roundToDecimal(double val, int places) {
  double mod = pow(10.0, places);
  return ((val * mod).round().toDouble() / mod);
}

Vector2 normalizeVector(Vector2 vector) {
  double magnitude = max(vector.x.abs(), vector.y.abs());

  // x and y both 0
  if (magnitude == 0.0) {
    return vector;
  }

  if (vector.x == 0) {
    vector.y = vector.y / magnitude;
    return vector;
  }

  if (vector.y == 0) {
    vector.x = vector.x / magnitude;
    return vector;
  }

  vector.x = vector.x / magnitude;
  vector.y = vector.y / magnitude;

  return vector;
}

Map<Asteroid, double> getasteroidsInDirection(
    Set<Asteroid> asteroids, Asteroid base, Vector2 direction) {
  Map<Asteroid, double> inDirectionWithDistance = Map<Asteroid, double>();

  for (Asteroid currAsteroid in asteroids) {
    // do check all asteroids in sight
    double vectorX = (currAsteroid.x - base.x).toDouble();
    double vectorY = (currAsteroid.y - base.y).toDouble();

    Vector2 vector2 = normalizeVector(Vector2(vectorX, vectorY));
    /*print('$base -> $currAsteroid');
    print(vector2);
    print('');
    */

    // make sure 0.6666... and 0.333 are not missed
    double roundedX = roundToDecimal(vector2.x, 2);
    double roundedY = roundToDecimal(vector2.y, 2);

    if ((direction.x == roundedX) && (direction.y == roundedY)) {
      inDirectionWithDistance.putIfAbsent(
          currAsteroid, () => (vectorX + vectorY).abs());
    }
  }

  return inDirectionWithDistance;
}

List<Asteroid> getAsteroidsInSight(
    List<Asteroid> asteroids, Asteroid asteroid) {
  List<Asteroid> inSight = [];
  Set<Vector2> vectors = Set();

  for (Asteroid currAsteroid in asteroids) {
    if (currAsteroid != asteroid) {
      // do check all asteroids in sight
      double vectorX = (currAsteroid.x - asteroid.x).toDouble();
      double vectorY = (currAsteroid.y - asteroid.y).toDouble();

      Vector2 vector2 = normalizeVector(Vector2(vectorX, vectorY));
      
      /*
      print('$asteroid -> $currAsteroid');
      print(vector2);
      print('');
      */
      
      if (!vectors.contains(vector2)) {
        vectors.add(vector2);
        inSight.add(currAsteroid);
      }
    }
  }

  asteroid.numOfVisibleAsteroids = vectors.length;
  return inSight;
}