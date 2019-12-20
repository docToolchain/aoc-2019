package aoc2019;

import static aoc2019.IntcodeComputer.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class DroidTest {
  private static final String program = "3,1033,1008,1033,1,1032,1005,1032,31,1008,1033,2,1032,1005,1032,58,1008,1033,3,1032,1005,1032,81,1008,1033,4,1032,1005,1032,104,99,1001,1034,0,1039,1001,1036,0,1041,1001,1035,-1,1040,1008,1038,0,1043,102,-1,1043,1032,1,1037,1032,1042,1105,1,124,102,1,1034,1039,1002,1036,1,1041,1001,1035,1,1040,1008,1038,0,1043,1,1037,1038,1042,1106,0,124,1001,1034,-1,1039,1008,1036,0,1041,1002,1035,1,1040,1001,1038,0,1043,1002,1037,1,1042,1106,0,124,1001,1034,1,1039,1008,1036,0,1041,1002,1035,1,1040,101,0,1038,1043,1001,1037,0,1042,1006,1039,217,1006,1040,217,1008,1039,40,1032,1005,1032,217,1008,1040,40,1032,1005,1032,217,1008,1039,35,1032,1006,1032,165,1008,1040,35,1032,1006,1032,165,1102,1,2,1044,1105,1,224,2,1041,1043,1032,1006,1032,179,1102,1,1,1044,1105,1,224,1,1041,1043,1032,1006,1032,217,1,1042,1043,1032,1001,1032,-1,1032,1002,1032,39,1032,1,1032,1039,1032,101,-1,1032,1032,101,252,1032,211,1007,0,44,1044,1106,0,224,1102,0,1,1044,1106,0,224,1006,1044,247,1001,1039,0,1034,1002,1040,1,1035,102,1,1041,1036,101,0,1043,1038,1001,1042,0,1037,4,1044,1106,0,0,9,21,23,46,38,21,77,24,34,41,9,82,3,32,97,21,67,23,67,35,41,27,93,13,82,38,74,16,91,25,34,64,47,43,50,15,81,21,30,27,63,88,9,98,95,42,69,23,57,15,52,22,65,43,7,36,90,13,8,83,68,37,6,48,22,53,21,87,86,77,23,14,56,40,32,77,15,9,70,2,28,88,35,37,98,91,29,84,4,62,75,99,40,57,68,35,79,47,78,41,88,20,92,24,76,8,8,51,16,21,75,97,15,71,34,21,77,26,5,98,92,13,94,36,39,61,78,19,96,12,28,3,68,17,8,83,29,50,10,17,46,9,18,56,2,75,53,47,12,66,18,62,67,10,73,35,69,33,58,39,24,68,17,90,77,35,83,22,98,46,6,46,41,45,69,33,12,70,21,47,13,25,54,36,53,23,83,6,31,33,79,55,29,55,42,9,53,25,29,66,60,83,37,9,56,35,2,28,50,84,92,1,50,40,1,59,93,5,85,82,31,74,34,70,28,37,51,50,31,24,83,62,36,29,16,9,93,49,40,13,50,51,54,23,66,88,46,15,31,90,10,59,38,87,36,32,54,71,35,6,24,43,76,53,17,60,41,64,66,12,5,84,22,47,24,94,39,40,51,20,33,61,35,10,9,97,8,79,56,19,59,41,91,67,9,12,70,55,78,78,31,25,45,3,62,10,87,20,17,54,66,14,28,58,3,12,94,80,4,93,93,18,70,92,7,43,30,99,21,81,68,23,19,75,49,42,37,72,14,17,16,50,77,12,33,92,84,26,83,35,52,32,53,5,49,3,94,72,39,51,41,64,4,99,77,67,30,60,52,4,1,75,96,10,12,54,58,4,66,62,84,38,2,46,83,12,33,99,17,3,42,64,84,38,62,6,72,42,20,82,30,36,63,27,75,11,65,16,36,79,9,58,33,48,56,20,11,13,41,65,28,99,15,31,56,89,26,58,5,13,93,24,11,4,25,49,83,96,15,93,60,2,8,86,76,10,41,60,53,13,45,70,33,35,88,38,76,75,26,88,73,52,19,32,88,17,65,35,23,3,74,93,40,77,19,10,57,1,53,12,84,32,39,96,16,55,38,77,52,24,1,58,5,90,88,33,78,36,16,61,22,36,76,64,23,38,56,18,67,32,86,53,21,76,52,34,57,4,19,1,74,67,9,61,80,9,35,31,80,12,97,28,41,72,24,38,64,25,87,21,54,15,84,55,9,33,16,52,51,37,79,43,54,20,98,33,45,89,18,25,33,9,12,52,27,67,62,92,27,95,35,47,13,52,22,63,51,19,50,50,40,19,90,13,67,49,18,83,6,58,9,62,16,74,20,16,51,56,90,36,50,3,48,26,50,31,24,74,83,73,10,55,90,83,4,1,46,21,88,26,56,35,10,77,2,40,90,14,68,27,62,38,6,61,66,10,8,72,35,79,74,38,76,46,43,83,25,25,75,11,18,74,18,3,59,94,22,42,79,85,9,10,26,78,27,13,94,28,57,25,19,59,1,89,54,84,41,9,71,6,30,73,29,58,87,43,61,17,66,9,69,23,58,36,11,45,86,45,28,62,97,6,31,19,99,65,36,58,36,45,3,26,27,33,46,75,19,97,24,65,75,33,15,21,83,98,38,29,77,83,15,62,7,51,86,12,11,37,7,86,9,80,37,92,28,50,52,69,16,55,76,59,9,85,30,97,69,93,13,63,4,74,80,88,31,80,36,51,40,98,95,83,23,92,7,91,63,68,40,73,0,0,21,21,1,10,1,0,0,0,0,0,0";

  private final BlockingQueue<Long> inputQueue  = new ArrayBlockingQueue<>(1000);
  private final   BlockingQueue<Long> outputQueue = new ArrayBlockingQueue<>(1000);
  private static final int north = 1;
  private static final int south = 2;
  private static final int west = 3;
  private static final int east = 4;
  private List<Coordinate> crossings = new ArrayList<>();
  private Coordinate oxygen = null;
  private int maxMinutes = 0;
  private int minDistance = Integer.MAX_VALUE;

  @Test
  void exploreTheMaze() throws InterruptedException {
    new Thread(() -> execute(inputQueue, outputQueue, program)).start();

    var map = new int[10000][10000];
    var status = 0;
    var x = 5000;
    var y = 5000;
    final var random = new Random(1);

    for(var i = 0; i < 1000000; i++) {
      var direction = random.nextInt(4)+1;
      inputQueue.add((long) direction);
      status = Math.toIntExact(outputQueue.poll(1, TimeUnit.SECONDS));
      switch (direction) {
        case north -> {
          map[x][y - 1] = status + 1;
          if (status != 0)
            y--;
        }
        case south -> {
          map[x][y + 1] = status + 1;
          if (status != 0)
            y++;
        }
        case west -> {
          map[x - 1][y] = status + 1;
          if (status != 0)
            x--;
        }
        case east -> {
          map[x + 1][y] = status + 1;
          if (status != 0)
            x++;
        }
        default -> throw new IllegalStateException("Unexpected value: " + direction);
      }

      if(status == 2) {
        System.out.println("Found the oxygen system.");
        break;
      }
    }

    var around = 30;
    for(var row = 5000 - around; row < 5000 + around; row++) {
      for(var col = 5000 - around; col < 5000 + around; col++) {
        var symbol = ' ';
        if(map[col][row] == 1)
          symbol = '#';
        if(map[col][row] == 2)
          symbol = '.';
        if(col == x && row == y)
          symbol = 'D';
        if(col == 5000 && row == 5000)
          symbol = 'X';
        System.out.print(symbol);
      }
      System.out.println();
    }

  }

  @Test
  void shortestPath() {
    Coordinate start = findAllCrossings();

    connectNodes(start);

    distanceToOxygen(start, 0);

    assertEquals(404, minDistance);
  }

  @Test
  void fillWithOxygen() {
    MapScanner mapScanner = new MapScanner().scan();
    int[][] map = mapScanner.getMap();

    fillWithOxygen(oxygen.x, oxygen.y, map, 0);

    assertEquals(406, maxMinutes);
  }

  private void connectNodes(Coordinate start) {
    MapScanner mapScanner;
    int[][] map;
    for (Coordinate aCrossing : crossings) {
      for (Integer possibleDirection : aCrossing.possibleDirections) {
        // each leg gets a fresh copy
        mapScanner = new MapScanner().scan();
        map = mapScanner.getMap();
        connectToNextNode(aCrossing, possibleDirection, map);
      }
    }
    // and also for start
    // each leg gets a fresh copy
    mapScanner = new MapScanner().scan();
    map = mapScanner.getMap();
    connectToNextNode(start, 2, map);
  }

  private Coordinate findAllCrossings() {
    MapScanner mapScanner = new MapScanner().scan();
    int[][] map = mapScanner.getMap();
    Coordinate start = mapScanner.getStart();

    for(var row = 1; row < 40; row++) {
      for (var col = 1; col < 40; col++) {
        if(map[col][row] == 0) continue;

        var possibleDirections = possibleDirections(col, row, map);
        if(possibleDirections.size() > 2) {
          var cross = new Coordinate(col, row, possibleDirections);
          crossings.add(cross);
        }
      }
    }
    return start;
  }

  private void fillWithOxygen(int x, int y, int[][] map, int minutes) {
    if(minutes > maxMinutes) maxMinutes = minutes;

    if (map[x][y] == -10) {
      return;
    }

    map[x][y] = -10;

    final var directions = possibleDirections(x, y, map);
    for (Integer direction : directions) {
      // step
      switch (direction) {
        case north -> fillWithOxygen(x, y-1, map, minutes + 1);
        case south -> fillWithOxygen(x, y+1, map, minutes + 1);
        case west -> fillWithOxygen(x - 1, y, map, minutes + 1);
        case east -> fillWithOxygen(x+1, y, map, minutes + 1);
        default -> throw new IllegalStateException("Unexpected value: " + direction);
      }
    }
  }

  private void distanceToOxygen(Coordinate start, int count) {
    start.visited = true;
    for (Map.Entry<Coordinate, Integer> toAndCount : start.relations.entrySet()) {
      final var coordinate = toAndCount.getKey();
      if(coordinate.equals(oxygen)) {
        final var dist = count + toAndCount.getValue();
        if(dist < minDistance) minDistance = dist;
        return;
      }

      if (!coordinate.visited) {
        distanceToOxygen(coordinate, count + toAndCount.getValue());
      }
    }

  }

  private List<Integer> possibleDirections(int x, int y, int[][] map) {
    var possibilities = new ArrayList<Integer>();
    if(map[x][y-1] >= 1)
      possibilities.add(1);
    if(map[x][y+1] >= 1)
      possibilities.add(2);
    if(map[x-1][y] >= 1)
      possibilities.add(3);
    if(map[x+1][y] >= 1)
      possibilities.add(4);
    return possibilities;
  }

  private void connectToNextNode(Coordinate start, int initialDirection, int[][] map) {
    var count = 0;
    int x = start.x;
    int y = start.y;
    var direction = initialDirection;

    for(var i = 0; i < 1000; i++) {
      map[x][y] = -100;
      count++;

      // step
      switch (direction) {
        case north -> y--;
        case south -> y++;
        case west -> x--;
        case east -> x++;
        default -> throw new IllegalStateException("Unexpected value: " + direction+ " for coordinate "+start);
      }

      var possibleDirections = countPossibleDirections(x, y, map);
      if (possibleDirections > 1) {
        for (Coordinate crossing : crossings) {
          if(crossing.x == x && crossing.y == y) {
            start.lengthTo(crossing, count);
          }
        }
        return;
      }

      direction = chooseDirection(x, y, map);
      if(direction == -1) {
        if (map[x][y] == 2) {
          // oxygen system found
          start.lengthTo(oxygen, count);
          return;
        }
        if (map[x][y] == 3) {
          // start node found
          return;
        }

        return; // dead end
      }
    }

    throw new IllegalStateException("No next crossing found.");
  }

  private int chooseDirection(int x, int y, int[][] map) {
    if(map[x][y-1] >= 1) return 1;
    if(map[x][y+1] >= 1) return 2;
    if(map[x-1][y] >= 1) return 3;
    if(map[x+1][y] >= 1) return 4;
    return -1;
  }

  private int countPossibleDirections(int x, int y, int[][] map) {
    var possibleDirections = 0;
    if(map[x][y-1] >= 1) possibleDirections++;
    if(map[x][y+1] >= 1) possibleDirections++;
    if(map[x-1][y] >= 1) possibleDirections++;
    if(map[x+1][y] >= 1) possibleDirections++;
    return possibleDirections;
  }

  private static class Coordinate {
    private final int x;
    private final int y;
    private final List<Integer> possibleDirections = new ArrayList<>();
    public boolean visited = false;

    private Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public Coordinate(int col, int row, List<Integer> possibleDirections) {
      this(col, row);
      this.possibleDirections.addAll(possibleDirections);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final Coordinate that = (Coordinate) o;
      return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public String toString() {
      return "(" + x + ", " + y + ')';
    }

    private final Map<Coordinate, Integer> relations = new HashMap<>();
    public void lengthTo(Coordinate crossing, int count) {
      relations.put(crossing, count);
    }
  }

  private static final String mapPainting = """
         #### ####### ################### ########
         #...#.......#...................#.......#
         #.#.#.###.#.#.#######.#########.#######.#
         #.#...#.#.#.#.#...#...#.......#...#.....#
         #.#####.#.#.#.#.#.#.###.###### ##.#.###.#
         #...#...#.#.#.#.#...#.........#...#.#...#
         ###.#.###.#.###.#############.#.###.#.###
         #...#.#...#.#...#.......#.....#.....#.#.#
         #.###.#.###.#.#.#.#####.#.###########.#.#
         #.....#.#.....#.#.#...#.#...........#.#.#
         #####.#.#######.#.###.#.#.#######.###.#.#
         #.#...#...#...#.#.#...#.#.......#...#...#
         #.#.#####.#.#.###.#.###.#######.###.###.#
         #...#...#...#...#.#.#...#.....#.#.#...#.#
         #.###.#########.#.#.#.###.#.###.#.###.#.#
         #.....#.........#.#.#...#.#...#.#.#...#.#
         #####.#.###.#####.#.###.## ##.#.#.#.###.#
         #.#...#.#...#...#.#...#...#...#...#.....#
         #.#.###.#####.#.#.###.###.#.#.###.#######
         #.#.#.#.......#...#...#...#.#.#...#.....#
         #.#.#.#############.###.###.###.###.###.#
         #.#.......#.........#X#.#.....#.#...#.#.#
         #.#######.#.#######.#.#.###.#.#.#.###.#.#
         #.#.....#...#...#...#.#...#.#...#...#.#.#
         #.#.#.#######.###.###.###.#.#### ##.#.#.#
         #...#.#.........#.#.#...#.#.....#...#...#
         #.###.#.#######.#.#.###.#.#####.#.###.###
         #.#.#...#.#...#...#...#.#.....#.#.#.#...#
         #.#.#####.#.#.#####.#.#.#.###.#.#.#.###.#
         #.#.........#.....#.#.#.#.#.#.#...#.....#
         #.###########.#.#.#.###.#.#.#.#####.#####
         #...........#.#.#.#.#...#...#...#.#.#...#
         ###########.###.#.#.#.###### ##.#.#.#.###
         #.........#...#.#...#.#.....#...#...#...#
         #.#######.###.#.#####.#.###.#.###.###.#.#
         #...#.#.....#...#.....#.#.#.#.#...#D..#.#
         ###.#.#.#######.#.#####.#.#.#.#.## ####.#
         #...#.#.#.....#.#.#.....#...#.#.#  ...#.#
         #.###.#.#.#.###.#.###.###.###.#.###.#.#.#
         #.....#...#.....#.....#.......#.....#...#
         ###### ### ##### ##### ####### ##### ####
        """;

  private class MapScanner {
    private int        x;
    private int        y;
    private int[][]    map;
    private Coordinate start;

    public MapScanner() {
    }

    public int[][] getMap() {
      return map;
    }

    public Coordinate getStart() {
      return start;
    }

    public MapScanner scan() {
      map = new int[41][41];
      var r = 0;
      for (String line : mapPainting.split("\n")) {
        var trimmed = line.trim();
        for(var col = 0; col < trimmed.length(); col++) {
          if(trimmed.charAt(col) == ' ') map[col][r] = 0;
          if(trimmed.charAt(col) == '#') map[col][r] = 0;
          if(trimmed.charAt(col) == '.') map[col][r] = 1;
          if(trimmed.charAt(col) == 'D') {
            map[col][r] = 2;
            oxygen = new Coordinate(col, r);
          }
          if(trimmed.charAt(col) == 'X') {
            map[col][r] = 1;
            x = col;
            y = r;
          }
        }
        r++;
      }

      start = new Coordinate(x, y);
      map[x][y] = 3;
      return this;
    }
  }
}
