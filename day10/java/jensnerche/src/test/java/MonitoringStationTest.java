import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Advent of Code, Day 10")
class MonitoringStationTest {

  @DisplayName("A coordinate is visible from another")
  @ParameterizedTest(name = "({1},{2}) should see ({3},{4}): {5}")
  @MethodSource("examplesForTestingVisibility")
  void thatACoordintateIsVisibleFromAnother(@ConvertWith(AsteriodMapConverter.class) String[] asteriodMap,
                                            int x1, int y1, int x2, int y2, boolean isVisible) {
    assertEquals(isVisible, asteriodsSeeEachOther(x1, y1, x2, y2, toAsteroidIsAtPosition(asteriodMap)));
  }

  @DisplayName("Find the best place for the station")
  @ParameterizedTest(name = "{index}. example")
  @MethodSource("asciiArtMapProvider")
  void thatTheBestPlaceForTheStationIsFound(@ConvertWith(AsteriodMapConverter.class) String[] asteriodMap,
                                  int expectedAsteroidCount, String bestPlace) {
    int[][] visibilityMatrix = createVisibilityMatrix(asteriodMap);
    String pos = "";

    // test still contains not-driven-out business code
    int bestPlaceSees = 0;
    for (int y1 = 0; y1 < asteriodMap.length; y1++) {
      for (int x1 = 0; x1 < asteriodMap[0].length(); x1++) {
        if(bestPlaceSees < visibilityMatrix[x1][y1]) {
          bestPlaceSees = visibilityMatrix[x1][y1];
          pos = "(" + x1 + "," + y1 + ")";
        }
      }
    }

    assertEquals(expectedAsteroidCount, bestPlaceSees,
                 "Expected "+expectedAsteroidCount+" but actual "+bestPlaceSees);
    assertEquals(bestPlace, pos);
  }

  @DisplayName("Day 10 Part 1 contained in one single test")
  @Test
  void day10Part2() {
    int x = 23;
    int y = 19;

    var map = """
         .#......#...#.....#..#......#..##..#
         ..#.......#..........#..##.##.......
         ##......#.#..#..#..##...#.##.###....
         ..#........#...........#.......##...
         .##.....#.......#........#..#.#.....
         .#...#...#.....#.##.......#...#....#
         #...#..##....#....#......#..........
         ....#......#.#.....#..#...#......#..
         ......###.......#..........#.##.#...
         #......#..#.....#..#......#..#..####
         .##...##......##..#####.......##....
         .....#...#.........#........#....#..
         ....##.....#...#........#.##..#....#
         ....#........#.###.#........#...#..#
         ....#..#.#.##....#.........#.....#.#
         ##....###....##..#..#........#......
         .....#.#.........#.......#....#....#
         .###.....#....#.#......#...##.##....
         ...##...##....##.........#...#......
         .....#....##....#..#.#.#...##.#...#.
         #...#.#.#.#..##.#...#..#..#..#......
         ......#...#...#.#.....#.#.....#.####
         ..........#..................#.#.##.
         ....#....#....#...#..#....#.....#...
         .#####..####........#...............
         #....#.#..#..#....##......#...#.....
         ...####....#..#......#.#...##.....#.
         ..##....#.###.##.#.##.#.....#......#
         ....#.####...#......###.....##......
         .#.....#....#......#..#..#.#..#.....
         ..#.......#...#........#.##...#.....
         #.....####.#..........#.#.......#...
         ..##..#..#.....#.#.........#..#.#.##
         .........#..........##.#.##.......##
         #..#.....#....#....#.#.......####..#
         ..............#.#...........##.#.#..
        """;

    var g = new AsteriodMapConverter().convert(map, null);
    var nrAsteroids = map.chars().filter(c -> c == '#').count();
    var currentAsteroidPositions = toAsteroidIsAtPosition(g);

    var rows = g.length;
    var cols = g[0].length();
    int count = 1;
    List<int[]> vapOrder = new ArrayList<>();

    while(count < nrAsteroids) {
      Map<Double, int[]> angleToCoordinates = new TreeMap<>();
      for (var row = 0; row < rows; row++) {
        for (var col = 0; col < cols; col++) {
          if (!currentAsteroidPositions[col][row])
            continue;

          if (col == x && row == y)
            continue;

          final boolean seeEachOtherPolar = asteriodsSeeEachOther(x, y, col, row, currentAsteroidPositions);
          if (seeEachOtherPolar) {
            var angle = angle(x, y, col, row);
            angleToCoordinates.put(angle, new int[] { col, row });
          }
        }
      }

      double start = Math.PI / 2 * -1;

      for (Map.Entry<Double, int[]> doubleEntry : angleToCoordinates.entrySet()) {
        if (doubleEntry.getKey() < start)
          continue;
        final int[] coordinate = doubleEntry.getValue();
        vapOrder.add(new int[] { coordinate[0], coordinate[1] });
        currentAsteroidPositions[coordinate[0]][coordinate[1]] = false;
        count++;
      }

      for (Map.Entry<Double, int[]> doubleEntry : angleToCoordinates.entrySet()) {
        if (doubleEntry.getKey() >= start)
          continue;
        final int[] coordinate = doubleEntry.getValue();
        vapOrder.add(new int[] { coordinate[0], coordinate[1] });
        currentAsteroidPositions[coordinate[0]][coordinate[1]] = false;
        count++;
      }
    } // end while

    final int[] vaprization200 = vapOrder.get(199);
    final int solution = vaprization200[0] * 100 + vaprization200[1];
    assertEquals(1417, solution);
  }


  private double angle(int x1, int y1, int x2, int y2) {
    int dx = x2 - x1;
    int dy = y2 - y1;
    double r = Math.sqrt(dx * dx + dy * dy);
    if(r == 0) throw new IllegalArgumentException("two points are same");
    final double acos = Math.acos(dx / r);
    return dy >= 0 ? acos : acos * -1;
  }

  private boolean asteriodsSeeEachOther(int x1, int y1, int x2, int y2, boolean[][] asteriodMap) {
    int dx = x2 - x1;
    int dy = y2 - y1;
    int gcd = gcd(dx, dy);
    if(gcd < 0) gcd *= -1;
    int stepX = dx / gcd;
    int stepY = dy / gcd;
    int currentX = x1 + stepX;
    int currentY = y1 + stepY;
    while(true) {
      if(currentX == x2 && currentY == y2) {
        return true;
      }

      if(asteriodMap[currentX][currentY]) {
        return false;
      }

      currentX += stepX;
      currentY += stepY;
    }
  }

  private void countVisibleAsteriods(String[] asteriodMap, int[][] map, boolean[][] isAsteroidOnCoordinate, int x1,
                                     int y1) {
    for (int x2 = 0; x2 < asteriodMap[0].length(); x2++) {
      for (int y2 = 0; y2 < asteriodMap.length; y2++) {
        if(!isAsteroidOnCoordinate[x1][y1]) {
          continue;
        }
        if(!isAsteroidOnCoordinate[x2][y2]) {
          continue;
        }
        if(x1 == x2 && y1 == y2) {
          continue;
        }

        if(asteriodsSeeEachOther(
            x1, y1, x2, y2, isAsteroidOnCoordinate)) {
          map[x1][y1]++;
        }
      }
    }
  }

  private boolean[][] toAsteroidIsAtPosition(String[] asteriodMap) {
    boolean[][] map = new boolean[asteriodMap[0].length()][asteriodMap.length];
    for (int y = 0; y < asteriodMap.length; y++) {
      for (int x = 0; x < asteriodMap[0].length(); x++) {
        final char c = asteriodMap[y].charAt(x);
        map[x][y] = c == '#';
      }
    }
    return map;
  }

  private int[][] createVisibilityMatrix(String[] asteriodMap) {
    int[][] map = new int[asteriodMap[0].length()][asteriodMap.length];
    final boolean[][] isAsteroidOnCoordinate = toAsteroidIsAtPosition(asteriodMap);

    for (int x1 = 0; x1 < asteriodMap[0].length(); x1++) {
      for (int y1 = 0; y1 < asteriodMap.length; y1++) {
        countVisibleAsteriods(asteriodMap, map, isAsteroidOnCoordinate, x1, y1);
      }
    }
    return map;
  }

  private int gcd(int dx, int dy) {
    // Euklid
    int a = dx;
    int b = dy;
    int h;
    while(b != 0) {
      h = a % b;
      a = b;
      b = h;
    }
    return a;
  }

  static Stream<Arguments> asciiArtMapProvider() {
    return Stream.of(
        Arguments.of(
            """
            .#..#
            .....
            #####
            ....#
            ...##
            """, 8, "(3,4)"
        ),
        Arguments.of("""
                         ......#.#.
                         #..#.#....
                         ..#######.
                         .#.#.###..
                         .#..#.....
                         ..#....#.#
                         #..#....#.
                         .##.#..###
                         ##...#..#.
                         .#....####
                         """,33, "(5,8)"
        ),
        Arguments.of("""
                         #.#...#.#.
                         .###....#.
                         .#....#...
                         ##.#.#.#.#
                         ....#.#.#.
                         .##..###.#
                         ..#...##..
                         ..##....##
                         ......#...
                         .####.###.
                         """,35, "(1,2)"
        ),
        Arguments.of("""
                         .#..#..###
                         ####.###.#
                         ....###.#.
                         ..###.##.#
                         ##.##.#.#.
                         ....###..#
                         ..#.#..#.#
                         #..#.#.###
                         .##...##.#
                         .....#.#..
                         """,41, "(6,3)"
        ),
        Arguments.of("""
                         .#..##.###...#######
                         ##.############..##.
                         .#.######.########.#
                         .###.#######.####.#.
                         #####.##.#.##.###.##
                         ..#####..#.#########
                         ####################
                         #.####....###.#.#.##
                         ##.#################
                         #####.##.###..####..
                         ..######..##.#######
                         ####.##.####...##..#
                         .#####..#.######.###
                         ##...#.##########...
                         #.##########.#######
                         .####.#.###.###.#.##
                         ....##.##.###..#####
                         .#.#.###########.###
                         #.#.#.#####.####.###
                         ###.##.####.##.#..##
                         """, 210, "(11,13)"
        ),
        Arguments.of("""
                         .#......#...#.....#..#......#..##..#
                         ..#.......#..........#..##.##.......
                         ##......#.#..#..#..##...#.##.###....
                         ..#........#...........#.......##...
                         .##.....#.......#........#..#.#.....
                         .#...#...#.....#.##.......#...#....#
                         #...#..##....#....#......#..........
                         ....#......#.#.....#..#...#......#..
                         ......###.......#..........#.##.#...
                         #......#..#.....#..#......#..#..####
                         .##...##......##..#####.......##....
                         .....#...#.........#........#....#..
                         ....##.....#...#........#.##..#....#
                         ....#........#.###.#........#...#..#
                         ....#..#.#.##....#.........#.....#.#
                         ##....###....##..#..#........#......
                         .....#.#.........#.......#....#....#
                         .###.....#....#.#......#...##.##....
                         ...##...##....##.........#...#......
                         .....#....##....#..#.#.#...##.#...#.
                         #...#.#.#.#..##.#...#..#..#..#......
                         ......#...#...#.#.....#.#.....#.####
                         ..........#..................#.#.##.
                         ....#....#....#...#..#....#.....#...
                         .#####..####........#...............
                         #....#.#..#..#....##......#...#.....
                         ...####....#..#......#.#...##.....#.
                         ..##....#.###.##.#.##.#.....#......#
                         ....#.####...#......###.....##......
                         .#.....#....#......#..#..#.#..#.....
                         ..#.......#...#........#.##...#.....
                         #.....####.#..........#.#.......#...
                         ..##..#..#.....#.#.........#..#.#.##
                         .........#..........##.#.##.......##
                         #..#.....#....#....#.#.......####..#
                         ..............#.#...........##.#.#..
                         """,278, "(23,19)"
        )

    );
  }

  static Stream<Arguments> examplesForTestingVisibility() {
    final String asciiArtMap = """
        .#..#
        .....
        #####
        ....#
        ...##
        """;
    return Stream.of(
        Arguments.of(asciiArtMap,4, 0, 1, 0, true),
        Arguments.of(asciiArtMap,1, 0, 3, 2, true),
        Arguments.of(asciiArtMap,1, 0, 4, 0, true),
        Arguments.of(asciiArtMap,1, 0, 0, 2, true),
        Arguments.of(asciiArtMap,1, 0, 1, 2, true),
        Arguments.of(asciiArtMap,1, 0, 2, 2, true),
        Arguments.of(asciiArtMap,1, 0, 4, 2, true),
        Arguments.of(asciiArtMap,1, 0, 4, 3, false),
        Arguments.of(asciiArtMap,4, 0, 4, 3, false),
        Arguments.of(asciiArtMap,4, 0, 4, 4, false)
    );
  }
}
