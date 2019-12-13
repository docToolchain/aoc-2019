import 'dart:io';

class Vector3 {
  int x;
  int y;
  int z;

  Vector3(this.x, this.y, this.z);

  Vector3 operator +(Vector3 o) {
    return Vector3(this.x + o.x, this.y + o.y, this.z + o.z);
  }

  @override
  String toString() {
    return '$x, $y, $z';
  }

  bool operator ==(o) {
    return (this.x == o.x) && (this.y == o.y) && (this.z == o.z);
  }

  @override
  int get hashCode => x * 1000000 + y * 10000 + z;
}

class Planet {
  Vector3 position;
  Vector3 velocity = Vector3(0, 0, 0);

  Planet(this.position);

  @override
  String toString() {
    return '(${position.x}, ${position.y}, ${position.z}) - (${velocity.x}, ${velocity.y}, ${velocity.z})';
  }

  bool operator ==(o) {
    bool positionEqual = this.position == o.position;
    bool velocityEqual = this.velocity == o.velocity;

    return (positionEqual && velocityEqual);
  }

  @override
  int get hashCode => position.hashCode * 10000 + velocity.hashCode;

  Vector3 getPlanetGravity(Planet v) {
    int x =
        (position.x > v.position.x) ? -1 : (position.x == v.position.x) ? 0 : 1;

    int y =
        (position.y > v.position.y) ? -1 : (position.y == v.position.y) ? 0 : 1;

    int z =
        (position.z > v.position.z) ? -1 : (position.z == v.position.z) ? 0 : 1;

    return Vector3(x, y, z);
  }

  int get totalEnergy {
    return (position.x.abs() + position.y.abs() + position.z.abs()) *
        (velocity.x.abs() + velocity.y.abs() + velocity.z.abs());
  }
}

/*
<x=13, y=9, z=5>
<x=8, y=14, z=-2>
<x=-5, y=4, z=11>
<x=2, y=-6, z=1>
*/
Vector3 parsePosition(String position) {
  RegExp regExp = new RegExp(
    r'.=([-]*\d+)',
    caseSensitive: false,
    multiLine: false,
  );

  List<int> values = regExp
      .allMatches(position)
      .map((match) => int.parse(match.group(1)))
      .toList();

  return Vector3(values[0], values[1], values[2]);
}

void applyGravity(List<Planet> planets) {
  for (Planet planetOut in planets) {
    for (Planet planetIn in planets) {
      if (!identical(planetOut, planetIn)) {
        Vector3 outPlanetGravity = planetOut.getPlanetGravity(planetIn);

        planetOut.velocity += outPlanetGravity;
      }
    }
  }

  for (Planet planet in planets) {
    planet.position += planet.velocity;
  }
}

int main(List<String> args) {
  File input = new File('./input.txt');

  List<Planet> planets = input
      .readAsStringSync()
      .split("\n")
      .map(parsePosition)
      .map((Vector3 position) => Planet(position))
      .toList();

  print(planets);

  for (int i = 0; i < 1000; i++) {
    applyGravity(planets);
  }

  print('Part 1');
  print(planets);
  print(planets
      .map((Planet planet) => planet.totalEnergy)
      .reduce((a, b) => a + b));
  print('');

  // re-reading for fresh planets
  planets = input
      .readAsStringSync()
      .split("\n")
      .map(parsePosition)
      .map((Vector3 position) => Planet(position))
      .toList();

  int i = 0;
  List<List<Planet>> seenStates = List<List<Planet>>();
  seenStates.add(planets);
  Map<String, int> cycles = Map<String, int>();

  print('Part 2');
  print(seenStates);

  while (true) {
    i++;
    List<Planet> nextState = List<Planet>.of(planets).map(copyPlanet).toList();

    applyGravity(nextState);

    bool isXCycle = checkCycle('x', nextState, seenStates);
    bool isYCycle = checkCycle('y', nextState, seenStates);
    bool isZCycle = checkCycle('z', nextState, seenStates);

    if (isXCycle) {
      print('Found x cycle after $i');
      cycles.putIfAbsent('x', () => i);
    }

    if (isYCycle) {
      print('Found y cycle after $i');
      cycles.putIfAbsent('y', () => i);
    }

    if (isZCycle) {
      print('Found z cycle after $i');
      cycles.putIfAbsent('z', () => i);
    }

    if (cycles.containsKey('x') &&
        cycles.containsKey('y') &&
        cycles.containsKey('z')) {
      break;
    }

    seenStates.add(nextState);
    planets = nextState;
  }

  int gdcXY = (cycles['x'] * cycles['y'] ~/ cycles['x'].gcd(cycles['y']));
  int gdcAll = (gdcXY * cycles['z'] ~/ gdcXY.gcd(cycles['z']));
  print(cycles);
  print(gdcAll);

  return 1;
}

bool checkCycle(
    String val, List<Planet> nextState, List<List<Planet>> seenStates) {
  for (List<Planet> states in seenStates) {
    int systemEquals = 0;

    for (int i = 0; i < states.length; i++) {
      Planet planet = nextState[i];
      Planet comparePlanet = states[i];

      switch (val) {
        case 'x':
          if ((planet.position.x == comparePlanet.position.x) &&
              (planet.velocity.x == comparePlanet.velocity.x)) {
            systemEquals++;
          }

          break;

        case 'y':
          if ((planet.position.y == comparePlanet.position.y) &&
              (planet.velocity.y == comparePlanet.velocity.y)) {
            systemEquals++;
          }

          break;

        case 'z':
          if ((planet.position.z == comparePlanet.position.z) &&
              (planet.velocity.z == comparePlanet.velocity.z)) {
            systemEquals++;
          }

          break;
        default:
          break;
      }
    }
    if (systemEquals == 4) {
      return true;
    }
  }

  return false;
}

Planet copyPlanet(Planet planet) {
  Vector3 pos =
      Vector3(planet.position.x, planet.position.y, planet.position.z);
  Vector3 veloc =
      Vector3(planet.velocity.x, planet.velocity.y, planet.velocity.z);

  Planet copied = Planet(pos);
  copied.velocity = veloc;

  return copied;
}
