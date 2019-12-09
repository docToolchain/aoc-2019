import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IntcodeComputerTest {
  private static final Logger                 LOGGER      = Logger.getLogger(IntcodeComputerTest.class.getName());

  private final BlockingQueue<Long> inputQueue  = new ArrayBlockingQueue<>(1000);
  private final BlockingQueue<Long> outputQueue = new ArrayBlockingQueue<>(1000);

  @BeforeEach
  void cleanup() {
    inputQueue.clear();
  }

  @Test
  void thatExample1Works() {
    String input = "1,9,10,3,2,3,11,0,99,30,40,50";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(input);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(3500, intcodeProgram[0]);
  }

  @Test
  void thatExample2Works() {
    String input = "1,0,0,0,99";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(input);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(2, intcodeProgram[0]);
  }

  @Test
  void thatExample3Works() {
    String input = "2,3,0,3,99";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(input);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(6, intcodeProgram[3]);
  }

  @Test
  void thatExample4Works() {
    String input = "2,4,4,5,99,0";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(input);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(9801, intcodeProgram[5]);
  }

  @Test
  void thatExample5Works() {
    String input = "1,1,1,4,99,5,6,0,99";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(input);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(30, intcodeProgram[0]);
  }

  @Test
  void thatOpcode3TakesAnInput() {
    String input = "3,0,99";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(input);
    inputQueue.add(30L);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(30, intcodeProgram[0]);
  }

  @Test
  void thatOpcode4OutputsAValue() {
    String program = "4,2,99";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(program);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(99, outputQueue.remove());
  }

  @Test
  void thatDay05InputOutputExampleWorks() {
    String program = "3,0,4,0,99";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add(42L);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(42, outputQueue.remove());
  }

  @Test
  void thatParameterModeWorks() {
    String program = "1002,4,3,4,33";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(program);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(99, intcodeProgram[4]);
  }

  @Test
  void thatNegativeIntegersWereTreated() {
    String program = "1101,100,-1,4,0";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(program);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(99, intcodeProgram[4]);
  }

  @ParameterizedTest(name = "when {0} is less than 8 the output is {1}")
  @CsvSource({"8,0","7,1"})
  void thatLessThanWorksInPositionMode(long input, long output) {
    String program = "3,9,7,9,10,9,4,9,99,-1,8";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add(input);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest(name = "when {0} is less than 8 the output is {1}")
  @CsvSource({"8,0","7,1"})
  void thatLessThanWorksInImmediateMode(long input, long output) {
    String program = "3,3,1107,-1,8,3,4,3,99";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add(input);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"8,1","7,0"})
  void thatEqualsWorksWithPositionMode(long input, long output) {
    String program = "3,9,8,9,10,9,4,9,99,-1,8";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add(input);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"8,1","7,0"})
  void thatEqualsWorksWithImmediateMode(long input, long output) {
    String program = "3,3,1108,-1,8,3,4,3,99";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add(input);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"0,0", "42,1"})
  void thatJumpsWorkInPositionMode(long input, long output) {
    String program = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add(input);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"0,0", "42,1"})
  void thatJumpsWorkInImmediateMode(long input, long output) {
    String program = "3,3,1105,-1,9,1101,0,0,12,4,12,99,1";
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add(input);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  private static final String day05Input = "3,225,1,225,6,6,1100,1,238,225,104,0,1102,79,14,225,1101,17,42,225,2,74,69,224,1001,224,-5733,224,4,224,1002,223,8,223,101,4,224,224,1,223,224,223,1002,191,83,224,1001,224,-2407,224,4,224,102,8,223,223,101,2,224,224,1,223,224,223,1101,18,64,225,1102,63,22,225,1101,31,91,225,1001,65,26,224,101,-44,224,224,4,224,102,8,223,223,101,3,224,224,1,224,223,223,101,78,13,224,101,-157,224,224,4,224,1002,223,8,223,1001,224,3,224,1,224,223,223,102,87,187,224,101,-4698,224,224,4,224,102,8,223,223,1001,224,4,224,1,223,224,223,1102,79,85,224,101,-6715,224,224,4,224,1002,223,8,223,1001,224,2,224,1,224,223,223,1101,43,46,224,101,-89,224,224,4,224,1002,223,8,223,101,1,224,224,1,223,224,223,1101,54,12,225,1102,29,54,225,1,17,217,224,101,-37,224,224,4,224,102,8,223,223,1001,224,3,224,1,223,224,223,1102,20,53,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,107,226,226,224,1002,223,2,223,1006,224,329,101,1,223,223,1108,677,226,224,1002,223,2,223,1006,224,344,101,1,223,223,7,677,226,224,102,2,223,223,1006,224,359,101,1,223,223,108,226,226,224,1002,223,2,223,1005,224,374,101,1,223,223,8,226,677,224,1002,223,2,223,1006,224,389,101,1,223,223,1108,226,226,224,102,2,223,223,1006,224,404,101,1,223,223,1007,677,677,224,1002,223,2,223,1006,224,419,101,1,223,223,8,677,677,224,1002,223,2,223,1005,224,434,1001,223,1,223,1008,226,226,224,102,2,223,223,1005,224,449,1001,223,1,223,1008,226,677,224,102,2,223,223,1006,224,464,101,1,223,223,1107,677,677,224,102,2,223,223,1006,224,479,101,1,223,223,107,677,677,224,1002,223,2,223,1005,224,494,1001,223,1,223,1107,226,677,224,1002,223,2,223,1005,224,509,101,1,223,223,1108,226,677,224,102,2,223,223,1006,224,524,101,1,223,223,7,226,226,224,1002,223,2,223,1005,224,539,101,1,223,223,108,677,677,224,1002,223,2,223,1005,224,554,101,1,223,223,8,677,226,224,1002,223,2,223,1005,224,569,1001,223,1,223,1008,677,677,224,102,2,223,223,1006,224,584,101,1,223,223,107,226,677,224,102,2,223,223,1005,224,599,1001,223,1,223,7,226,677,224,102,2,223,223,1005,224,614,101,1,223,223,1007,226,226,224,1002,223,2,223,1005,224,629,101,1,223,223,1107,677,226,224,1002,223,2,223,1006,224,644,101,1,223,223,108,226,677,224,102,2,223,223,1006,224,659,101,1,223,223,1007,677,226,224,102,2,223,223,1006,224,674,101,1,223,223,4,223,99,226";

  @Test
  void day05Part1() {
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(day05Input);
    inputQueue.add(1L);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    LOGGER.info(format("Output queue has %d elements", outputQueue.size()));
    for (Long output : outputQueue) {
      LOGGER.info(format("Program output: %d", output));
    }
  }

  @Test
  void day05Part2() {
    final long[] intcodeProgram = IntcodeComputer.reinitializeMemory(day05Input);
    inputQueue.add(5L);

    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    LOGGER.info(format("Output queue has %d elements", outputQueue.size()));
    for (Long output : outputQueue) {
      LOGGER.info(format("Program output: %d", output));
    }
  }

  @Test
  void day07Example1() {
    String program = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";

    final long maxOutputSignal = AmplifierSequence.maxOutputSignal(program);

    assertEquals(43210L, maxOutputSignal);
  }

  @Test
  void day07Example2() {
    String program = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0";

    final long maxOutputSignal = AmplifierSequence.maxOutputSignal(program);

    assertEquals(54321L, maxOutputSignal);
  }

  @Test
  void day07Example3() {
    String program = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0";

    final long maxOutputSignal = AmplifierSequence.maxOutputSignal(program);

    assertEquals(65210L, maxOutputSignal);
  }

  @Test
  void day07Part1() {
    String program = "3,8,1001,8,10,8,105,1,0,0,21,42,67,84,109,126,207,288,369,450,99999,3,9,102,4,9,9,1001,9,4,9,102,2,9,9,101,2,9,9,4,9,99,3,9,1001,9,5,9,1002,9,5,9,1001,9,5,9,1002,9,5,9,101,5,9,9,4,9,99,3,9,101,5,9,9,1002,9,3,9,1001,9,2,9,4,9,99,3,9,1001,9,2,9,102,4,9,9,101,2,9,9,102,4,9,9,1001,9,2,9,4,9,99,3,9,102,2,9,9,101,5,9,9,1002,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,99";

    final long maxOutputSignal = AmplifierSequence.maxOutputSignal(program);

    assertEquals(298586L, maxOutputSignal);
  }

  @Test
  void day07Part2Example1() {

    long thrusterSignal = AmplifierSequence.maxOutputSignalWithFeedback(
        "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"
    );

    assertEquals(139629729L, thrusterSignal);
  }

  @Test
  void day07Part2Example2() {

    long thrusterSignal = AmplifierSequence.maxOutputSignalWithFeedback(
      "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10"
    );

    assertEquals(18216L, thrusterSignal);
  }

  @Test
  void day07Part2() {
    String program = "3,8,1001,8,10,8,105,1,0,0,21,42,67,84,109,126,207,288,369,450,99999,3,9,102,4,9,9,1001,9,4,9,102,2,9,9,101,2,9,9,4,9,99,3,9,1001,9,5,9,1002,9,5,9,1001,9,5,9,1002,9,5,9,101,5,9,9,4,9,99,3,9,101,5,9,9,1002,9,3,9,1001,9,2,9,4,9,99,3,9,1001,9,2,9,102,4,9,9,101,2,9,9,102,4,9,9,1001,9,2,9,4,9,99,3,9,102,2,9,9,101,5,9,9,1002,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,99";

    final long maxOutputSignal = AmplifierSequence.maxOutputSignalWithFeedback(program);

    assertEquals(9246095L, maxOutputSignal);
  }

  @Test
  void thatRelativeAddressesWork() {
    final String input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
    final long[] expectedOutput = IntcodeComputer.reinitializeMemory(input);
    new IntcodeComputer()
        .interpret(inputQueue, outputQueue, input);

    for (long out : expectedOutput) {
      assertEquals(out, outputQueue.remove());

    }
  }

  @Test
  void thatLargeNumbersWork() {
    final String input = "1102,34915192,34915192,7,4,7,99,0";
    new IntcodeComputer()
        .interpret(inputQueue, outputQueue, input);

    assertEquals(1219070632396864L, outputQueue.remove());
  }

  @Test
  void thatDay09Example3Works() {
    final String input = "104,1125899906842624,99";
    new IntcodeComputer()
        .interpret(inputQueue, outputQueue, input);

    assertEquals(1125899906842624L, outputQueue.remove());
  }

  @Test
  void day09Part1() {
    inputQueue.add(1L);
    new IntcodeComputer()
        .interpret(inputQueue, outputQueue,
                   "1102,34463338,34463338,63,1007,63,34463338,63,1005,63,53,1102,1,3,1000,109,988,209,12,9,1000,209,6,209,3,203,0,1008,1000,1,63,1005,63,65,1008,1000,2,63,1005,63,904,1008,1000,0,63,1005,63,58,4,25,104,0,99,4,0,104,0,99,4,17,104,0,99,0,0,1101,39,0,1004,1101,0,37,1013,1101,0,28,1001,1101,0,38,1005,1101,23,0,1008,1102,1,0,1020,1102,1,26,1010,1102,31,1,1009,1101,29,0,1015,1102,459,1,1024,1101,33,0,1007,1101,0,30,1016,1101,32,0,1002,1102,1,494,1027,1101,0,216,1029,1101,497,0,1026,1101,0,303,1022,1102,1,21,1018,1102,1,36,1006,1102,1,27,1014,1102,296,1,1023,1102,454,1,1025,1102,35,1,1003,1101,22,0,1017,1102,225,1,1028,1102,1,20,1011,1101,1,0,1021,1101,0,24,1000,1101,0,25,1019,1101,0,34,1012,109,13,21102,40,1,0,1008,1013,40,63,1005,63,203,4,187,1106,0,207,1001,64,1,64,1002,64,2,64,109,5,2106,0,10,4,213,1001,64,1,64,1105,1,225,1002,64,2,64,109,-3,1206,6,241,1001,64,1,64,1105,1,243,4,231,1002,64,2,64,109,-17,2108,30,4,63,1005,63,259,1106,0,265,4,249,1001,64,1,64,1002,64,2,64,109,14,2108,35,-9,63,1005,63,283,4,271,1105,1,287,1001,64,1,64,1002,64,2,64,109,13,2105,1,-2,1001,64,1,64,1106,0,305,4,293,1002,64,2,64,109,-28,1208,5,32,63,1005,63,327,4,311,1001,64,1,64,1106,0,327,1002,64,2,64,109,12,2102,1,0,63,1008,63,31,63,1005,63,353,4,333,1001,64,1,64,1105,1,353,1002,64,2,64,109,7,21102,41,1,-6,1008,1010,40,63,1005,63,373,1105,1,379,4,359,1001,64,1,64,1002,64,2,64,109,-4,2102,1,-6,63,1008,63,35,63,1005,63,403,1001,64,1,64,1105,1,405,4,385,1002,64,2,64,109,11,21107,42,43,-4,1005,1019,427,4,411,1001,64,1,64,1105,1,427,1002,64,2,64,109,-10,1206,7,445,4,433,1001,64,1,64,1105,1,445,1002,64,2,64,109,10,2105,1,1,4,451,1105,1,463,1001,64,1,64,1002,64,2,64,109,-14,21108,43,42,4,1005,1013,479,1106,0,485,4,469,1001,64,1,64,1002,64,2,64,109,12,2106,0,6,1106,0,503,4,491,1001,64,1,64,1002,64,2,64,109,-10,2107,30,-2,63,1005,63,521,4,509,1106,0,525,1001,64,1,64,1002,64,2,64,109,-7,2101,0,-4,63,1008,63,26,63,1005,63,549,1001,64,1,64,1106,0,551,4,531,1002,64,2,64,109,13,21107,44,43,-3,1005,1014,571,1001,64,1,64,1105,1,573,4,557,1002,64,2,64,109,-6,21108,45,45,1,1005,1012,591,4,579,1106,0,595,1001,64,1,64,1002,64,2,64,109,8,1205,2,609,4,601,1106,0,613,1001,64,1,64,1002,64,2,64,109,-11,1208,-6,34,63,1005,63,629,1106,0,635,4,619,1001,64,1,64,1002,64,2,64,109,-15,2107,33,9,63,1005,63,651,1106,0,657,4,641,1001,64,1,64,1002,64,2,64,109,9,1207,2,38,63,1005,63,677,1001,64,1,64,1106,0,679,4,663,1002,64,2,64,109,8,21101,46,0,0,1008,1010,45,63,1005,63,703,1001,64,1,64,1106,0,705,4,685,1002,64,2,64,109,-5,1201,-3,0,63,1008,63,32,63,1005,63,727,4,711,1106,0,731,1001,64,1,64,1002,64,2,64,109,-6,1207,8,34,63,1005,63,753,4,737,1001,64,1,64,1106,0,753,1002,64,2,64,109,29,1205,-8,765,1106,0,771,4,759,1001,64,1,64,1002,64,2,64,109,-18,1202,-6,1,63,1008,63,39,63,1005,63,797,4,777,1001,64,1,64,1106,0,797,1002,64,2,64,109,8,21101,47,0,0,1008,1018,47,63,1005,63,823,4,803,1001,64,1,64,1105,1,823,1002,64,2,64,109,-12,2101,0,-3,63,1008,63,35,63,1005,63,845,4,829,1106,0,849,1001,64,1,64,1002,64,2,64,109,-9,1201,5,0,63,1008,63,30,63,1005,63,869,1105,1,875,4,855,1001,64,1,64,1002,64,2,64,109,8,1202,-2,1,63,1008,63,34,63,1005,63,899,1001,64,1,64,1105,1,901,4,881,4,64,99,21101,27,0,1,21101,0,915,0,1105,1,922,21201,1,45467,1,204,1,99,109,3,1207,-2,3,63,1005,63,964,21201,-2,-1,1,21101,942,0,0,1106,0,922,21201,1,0,-1,21201,-2,-3,1,21102,1,957,0,1105,1,922,22201,1,-1,-2,1105,1,968,22101,0,-2,-2,109,-3,2106,0,0");

    assertEquals(2890527621L, outputQueue.remove());
  }

  @Test
  void day09Part2() {
    inputQueue.add(2L);
    new IntcodeComputer()
        .interpret(inputQueue, outputQueue,
                   "1102,34463338,34463338,63,1007,63,34463338,63,1005,63,53,1102,1,3,1000,109,988,209,12,9,1000,209,6,209,3,203,0,1008,1000,1,63,1005,63,65,1008,1000,2,63,1005,63,904,1008,1000,0,63,1005,63,58,4,25,104,0,99,4,0,104,0,99,4,17,104,0,99,0,0,1101,39,0,1004,1101,0,37,1013,1101,0,28,1001,1101,0,38,1005,1101,23,0,1008,1102,1,0,1020,1102,1,26,1010,1102,31,1,1009,1101,29,0,1015,1102,459,1,1024,1101,33,0,1007,1101,0,30,1016,1101,32,0,1002,1102,1,494,1027,1101,0,216,1029,1101,497,0,1026,1101,0,303,1022,1102,1,21,1018,1102,1,36,1006,1102,1,27,1014,1102,296,1,1023,1102,454,1,1025,1102,35,1,1003,1101,22,0,1017,1102,225,1,1028,1102,1,20,1011,1101,1,0,1021,1101,0,24,1000,1101,0,25,1019,1101,0,34,1012,109,13,21102,40,1,0,1008,1013,40,63,1005,63,203,4,187,1106,0,207,1001,64,1,64,1002,64,2,64,109,5,2106,0,10,4,213,1001,64,1,64,1105,1,225,1002,64,2,64,109,-3,1206,6,241,1001,64,1,64,1105,1,243,4,231,1002,64,2,64,109,-17,2108,30,4,63,1005,63,259,1106,0,265,4,249,1001,64,1,64,1002,64,2,64,109,14,2108,35,-9,63,1005,63,283,4,271,1105,1,287,1001,64,1,64,1002,64,2,64,109,13,2105,1,-2,1001,64,1,64,1106,0,305,4,293,1002,64,2,64,109,-28,1208,5,32,63,1005,63,327,4,311,1001,64,1,64,1106,0,327,1002,64,2,64,109,12,2102,1,0,63,1008,63,31,63,1005,63,353,4,333,1001,64,1,64,1105,1,353,1002,64,2,64,109,7,21102,41,1,-6,1008,1010,40,63,1005,63,373,1105,1,379,4,359,1001,64,1,64,1002,64,2,64,109,-4,2102,1,-6,63,1008,63,35,63,1005,63,403,1001,64,1,64,1105,1,405,4,385,1002,64,2,64,109,11,21107,42,43,-4,1005,1019,427,4,411,1001,64,1,64,1105,1,427,1002,64,2,64,109,-10,1206,7,445,4,433,1001,64,1,64,1105,1,445,1002,64,2,64,109,10,2105,1,1,4,451,1105,1,463,1001,64,1,64,1002,64,2,64,109,-14,21108,43,42,4,1005,1013,479,1106,0,485,4,469,1001,64,1,64,1002,64,2,64,109,12,2106,0,6,1106,0,503,4,491,1001,64,1,64,1002,64,2,64,109,-10,2107,30,-2,63,1005,63,521,4,509,1106,0,525,1001,64,1,64,1002,64,2,64,109,-7,2101,0,-4,63,1008,63,26,63,1005,63,549,1001,64,1,64,1106,0,551,4,531,1002,64,2,64,109,13,21107,44,43,-3,1005,1014,571,1001,64,1,64,1105,1,573,4,557,1002,64,2,64,109,-6,21108,45,45,1,1005,1012,591,4,579,1106,0,595,1001,64,1,64,1002,64,2,64,109,8,1205,2,609,4,601,1106,0,613,1001,64,1,64,1002,64,2,64,109,-11,1208,-6,34,63,1005,63,629,1106,0,635,4,619,1001,64,1,64,1002,64,2,64,109,-15,2107,33,9,63,1005,63,651,1106,0,657,4,641,1001,64,1,64,1002,64,2,64,109,9,1207,2,38,63,1005,63,677,1001,64,1,64,1106,0,679,4,663,1002,64,2,64,109,8,21101,46,0,0,1008,1010,45,63,1005,63,703,1001,64,1,64,1106,0,705,4,685,1002,64,2,64,109,-5,1201,-3,0,63,1008,63,32,63,1005,63,727,4,711,1106,0,731,1001,64,1,64,1002,64,2,64,109,-6,1207,8,34,63,1005,63,753,4,737,1001,64,1,64,1106,0,753,1002,64,2,64,109,29,1205,-8,765,1106,0,771,4,759,1001,64,1,64,1002,64,2,64,109,-18,1202,-6,1,63,1008,63,39,63,1005,63,797,4,777,1001,64,1,64,1106,0,797,1002,64,2,64,109,8,21101,47,0,0,1008,1018,47,63,1005,63,823,4,803,1001,64,1,64,1105,1,823,1002,64,2,64,109,-12,2101,0,-3,63,1008,63,35,63,1005,63,845,4,829,1106,0,849,1001,64,1,64,1002,64,2,64,109,-9,1201,5,0,63,1008,63,30,63,1005,63,869,1105,1,875,4,855,1001,64,1,64,1002,64,2,64,109,8,1202,-2,1,63,1008,63,34,63,1005,63,899,1001,64,1,64,1105,1,901,4,881,4,64,99,21101,27,0,1,21101,0,915,0,1105,1,922,21201,1,45467,1,204,1,99,109,3,1207,-2,3,63,1005,63,964,21201,-2,-1,1,21101,942,0,0,1106,0,922,21201,1,0,-1,21201,-2,-3,1,21102,1,957,0,1105,1,922,22201,1,-1,-2,1105,1,968,22101,0,-2,-2,109,-3,2106,0,0");

    assertEquals(66772L, outputQueue.remove());
  }

}
