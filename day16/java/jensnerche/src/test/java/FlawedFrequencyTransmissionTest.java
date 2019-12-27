import static java.lang.Math.abs;
import static java.lang.Math.min;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FlawedFrequencyTransmissionTest {

  @Test
  void convertInput() {
    var input = "15243";

    final var ints = asIntArray(input);

    assertArrayEquals(new int[]{1,5,2,4,3}, ints);
  }

  @Test
  void thatKeepOneDigitWorks() {
    assertEquals(8, keepOneDigit(38));
    assertEquals(7, keepOneDigit(-17));
  }

  @Test
  void onePhase() {
    var input = "12345678";
    var in = asIntArray(input);

    in = onePhase(in);

    assertArrayEquals(new int[]{4,8,2,2,6,1,5,8}, in);
  }

  @Test
  void twoPhases() {
    var input = "12345678";
    var in = asIntArray(input);

    in = executePhases(in, 2);

    assertArrayEquals(new int[]{3,4,0,4,0,4,3,8}, in);
  }

  @ParameterizedTest(name = "{0} becomes after {1} phases {2}")
  @CsvSource({
      "12345678, 1, 48226158",
      "12345678, 4, 01029498",
      "80871224585914546619083218645595, 100, 24176176480919046114038763195595"
  })
  void thatACoordintateIsVisibleFromAnother(String input, int phases, String output) {
    var in = asIntArray(input);

    in = executePhases(in, phases);

    assertArrayEquals(asIntArray(output), in);
  }

  @Test
  void day16part1() {
    var input = "59780176309114213563411626026169666104817684921893071067383638084250265421019328368225128428386936441394524895942728601425760032014955705443784868243628812602566362770025248002047665862182359972049066337062474501456044845186075662674133860649155136761608960499705430799727618774927266451344390608561172248303976122250556049804603801229800955311861516221350410859443914220073199362772401326473021912965036313026340226279842955200981164839607677446008052286512958337184508094828519352406975784409736797004839330203116319228217356639104735058156971535587602857072841795273789293961554043997424706355960679467792876567163751777958148340336385972649515437";
    var in = asIntArray(input);

    in = executePhases(in, 100);
    final var solution = Arrays.toString(in).replaceAll("[^0-9]", "").substring(0, 8);

    assertEquals("52611030", solution);
  }

  @DisplayName("An algorithm special for the given input and the digits to skip > half of the input length")
  @Test
  void day16part2() {
    var input = "59780176309114213563411626026169666104817684921893071067383638084250265421019328368225128428386936441394524895942728601425760032014955705443784868243628812602566362770025248002047665862182359972049066337062474501456044845186075662674133860649155136761608960499705430799727618774927266451344390608561172248303976122250556049804603801229800955311861516221350410859443914220073199362772401326473021912965036313026340226279842955200981164839607677446008052286512958337184508094828519352406975784409736797004839330203116319228217356639104735058156971535587602857072841795273789293961554043997424706355960679467792876567163751777958148340336385972649515437";
    var digitsToSkip = 5978017;

    var in = asIntArray(input.repeat(10000).substring(digitsToSkip));

    for(var phase = 0; phase < 100; phase++) {
      var out = new int[in.length];
      out[out.length - 1] = in[out.length - 1];
      for (var i = out.length - 2; i >= 0; i--) {
        out[i] = keepOneDigit(out[i + 1] + in[i]);
      }
      in = out;
    }

    assertEquals(5, in[0]);
    assertEquals(2, in[1]);
    assertEquals(5, in[2]);
    assertEquals(4, in[3]);
    assertEquals(1, in[4]);
    assertEquals(0, in[5]);
    assertEquals(2, in[6]);
    assertEquals(6, in[7]);
  }

  private int[] executePhases(int[] in, int i) {
    int[] tmp = in;
    for(var p = 0; p < i; p++) {
      tmp = onePhase(tmp);
    }
    return tmp;
  }

  private int[] onePhase(int[] in) {
    var threads = 2;
    final var len = in.length;
    var result = new int[len];
    var latch = new CountDownLatch(threads);
    final var i1 = len / threads;

    for(var t = 0; t < threads; t++) {
      final int finalT = t;
      new Thread(() -> {
        for (var i = finalT * i1; i < (finalT + 1)*i1; i++) {
          result[i] = keepOneDigit(plutimikation(in, (i + 1)));
        }
        latch.countDown();
      }).start();
    }


    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return result;
  }

  private int keepOneDigit(long number) {
    return (int) abs(number % 10L);
  }

  int plutimikation(int[] in, int position) {
    var idx = -1; // skip the very first value exactly once
    var result = 0L;
    var len = in.length;

    while(idx < len) {
      idx += position; // skip 0
      if (idx >= len)
        return keepOneDigit(result);

      var e = min(idx + position, len);
      for (; idx < e; idx++) {
        result += in[idx];
      }

      idx += position; // skip 0
      if (idx >= len)
        return keepOneDigit(result);

      e = min(idx + position, len);
      for (; idx < e; idx++) {
        result -= in[idx];
      }
    }
    return keepOneDigit(result);
  }


  private int[] asIntArray(String input) {
    return input.chars().map(i -> i - 0x30).toArray();
  }
}
