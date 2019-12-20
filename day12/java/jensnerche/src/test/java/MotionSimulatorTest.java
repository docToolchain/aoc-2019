import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MotionSimulatorTest {
  private static Stream<Arguments> day12Input() {
    return Stream.of(Arguments.of(puzzleInput));
  }

  private static final String puzzleInput = """
      <x=-16, y=-1, z=-12>
      <x=0, y=-4, z=-17>
      <x=-11, y=11, z=0>
      <x=2, y=2, z=-6>
      """;

  private static final String example1 = """
      <x=-1, y=0, z=2>
      <x=2, y=-10, z=-7>
      <x=4, y=-8, z=8>
      <x=3, y=5, z=-1>
      """;

  private static final String example2 = """
      <x=-8, y=-10, z=0>
      <x=5, y=5, z=10>
      <x=2, y=-7, z=3>
      <x=9, y=-8, z=-3>
      """;

  @DisplayName("What is the total energy in the system?")
  @ParameterizedTest
  @MethodSource("day12Input")
  void day12Part1(@ConvertWith(PositionConverter.class) List<ThreeDimensional> positions) {
    var velocities = List
        .of(new ThreeDimensional(), new ThreeDimensional(), new ThreeDimensional(), new ThreeDimensional());

    //<editor-fold desc="Business Logic to be driven out">
    var totalEngergy = 0;
    var timeSteps = 1000;
    while (timeSteps-- > 0) {
      updateVelocity(velocities, positions);
      updatePosition(velocities, positions);

      totalEngergy = 0;
      for (var i = 0; i < positions.size(); i++) {
        totalEngergy += positions.get(i).energy() * velocities.get(i).energy();
      }
    }
    //</editor-fold>

    assertEquals(5517, totalEngergy);
  }

  @DisplayName("How many steps does it take to reach the first state that exactly matches a previous state?")
  @ParameterizedTest
  @MethodSource("day12Input")
  void day12Part2(@ConvertWith(PositionConverter.class) List<ThreeDimensional> positions) {
    var velocities = List
        .of(new ThreeDimensional(), new ThreeDimensional(), new ThreeDimensional(), new ThreeDimensional());

    //<editor-fold desc="Business Logic to be driven out">
    int steps = 0;
    final var maxSteps = 4000000;
    var histories = new int[24][maxSteps];
    while (steps < maxSteps) {
      updateVelocity(velocities, positions);
      updatePosition(velocities, positions);

      histories[0][steps] = positions .get(0).x;
      histories[1][steps] = positions .get(0).y;
      histories[2][steps] = positions .get(0).z;
      histories[3][steps] = velocities.get(0).x;
      histories[4][steps] = velocities.get(0).y;
      histories[5][steps] = velocities.get(0).z;

      histories[6][steps]  = positions .get(1).x;
      histories[7][steps]  = positions .get(1).y;
      histories[8][steps]  = positions .get(1).z;
      histories[9][steps]  = velocities.get(1).x;
      histories[10][steps] = velocities.get(1).y;
      histories[11][steps] = velocities.get(1).z;

      histories[12][steps] = positions .get(2).x;
      histories[13][steps] = positions .get(2).y;
      histories[14][steps] = positions .get(2).z;
      histories[15][steps] = velocities.get(2).x;
      histories[16][steps] = velocities.get(2).y;
      histories[17][steps] = velocities.get(2).z;

      histories[18][steps] = positions .get(3).x;
      histories[19][steps] = positions .get(3).y;
      histories[20][steps] = positions .get(3).z;
      histories[21][steps] = velocities.get(3).x;
      histories[22][steps] = velocities.get(3).y;
      histories[23][steps] = velocities.get(3).z;

      steps++;
    }

    var cycles = new int[24];
    for(var i = 0; i < 24; i++) {
      cycles[i] = period(histories[i]);
    }
    var lcm = lcm(cycles);
    //</editor-fold>

    assertEquals(303070460651184L, lcm);
  }

  private int period(int[] history) {
    var n = history.length;
    for(var i = 0; i < n; i++) {
      for(var j = i + 1; j < n; j++) {
        var lcp = lcp(history, i, j);
        if(lcp > 0) {
          return lcp;
        }
      }
    }
    return 0;
  }

  private int lcp(int[] history, int i, int j) {
    var n = Math.max(i, j);

    for(var x = 0; x < history.length - n; x++)
    if(history[i+x] != history[j+x]) {
      return 0;
    }

    return Math.abs(i-j);
  }

  static long gcd(long a, long b){
    return (b == 0) ? a : gcd(b, a % b);
  }

  static BigInteger lcm(BigInteger a, long b){
    return a.multiply(BigInteger.valueOf(b)).divide(BigInteger.valueOf(gcd(a.longValue(),b)));
  }

  static long lcm(int... args) {
    BigInteger r = BigInteger.valueOf(args[0]);
    int i = 0;
    while(i < args.length - 1) {
      r = lcm(r,args[++i]);
    }
    return r.longValue();
  }

  private void updateVelocity(List<ThreeDimensional> velocities, List<ThreeDimensional> positions) {
    for (var i = 0; i < positions.size() - 1; i++) {
      for (var k = i + 1; k < positions.size(); k++) {
        var p1 = positions.get(i);
        var p2 = positions.get(k);
        var dxp1 = p2.x.compareTo(p1.x);
        var dxp2 = -dxp1;
        var dyp1 = p2.y.compareTo(p1.y);
        var dyp2 = -dyp1;
        var dzp1 = p2.z.compareTo(p1.z);
        var dzp2 = -dzp1;
        velocities.get(i).delta(dxp1, dyp1, dzp1);
        velocities.get(k).delta(dxp2, dyp2, dzp2);
      }
    }
  }

  private void updatePosition(List<ThreeDimensional> velocities, List<ThreeDimensional> positions) {
    for (var i = 0; i < positions.size(); i++) {
      positions.get(i).delta(velocities.get(i));
    }
  }

  private static class PositionConverter implements ArgumentConverter {
    @Override
    public List<ThreeDimensional> convert(Object o, ParameterContext parameterContext)
        throws ArgumentConversionException {
      return Arrays.stream(((String) o).split("\n")).map(String::trim).map(ThreeDimensional::new)
                   .collect(Collectors.toList());
    }
  }

  private static class ThreeDimensional {
    private Integer x;
    private Integer y;
    private Integer z;

    public ThreeDimensional() {
      this(0, 0, 0);
    }

    ThreeDimensional copy() {
      return new ThreeDimensional(x, y, z);
    }

    private ThreeDimensional(String line) {
      this(Arrays.stream(
          line.replaceAll("<", "").replaceAll(">", "").replaceAll("x=", "").replaceAll("y=", "").replaceAll("z=", "")
              .split(",")).map(String::trim).map(Integer::valueOf).toArray(Integer[]::new));
    }

    private ThreeDimensional(Integer... xyz) {
      this.x = xyz[0];
      this.y = xyz[1];
      this.z = xyz[2];
    }

    public void delta(int dx, int dy, int dz) {
      x += dx;
      y += dy;
      z += dz;
    }

    public void delta(ThreeDimensional threeDimensional) {
      x += threeDimensional.x;
      y += threeDimensional.y;
      z += threeDimensional.z;
    }

    int energy() {
      return abs(x) + abs(y) + abs(z);
    }

    @Override
    public String toString() {
      return "<" + "x=" + x + ", y=" + y + ", z=" + z + '>';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final ThreeDimensional that = (ThreeDimensional) o;
      return Objects.equals(x, that.x) && Objects.equals(y, that.y) && Objects.equals(z, that.z);
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y, z);
    }
  }

}
