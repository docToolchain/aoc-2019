import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IntcodeComputerTest {
  private static final Logger LOGGER = Logger.getLogger(IntcodeComputerTest.class.getName());
  private static final Queue<Integer> inputQueue = new ArrayDeque<>();
  private static final Queue<Integer> outputQueue = new ArrayDeque<>();

  @BeforeEach
  void cleanup() {
    inputQueue.clear();
  }

  @Test
  void thatExample1Works() {
    String input = "1,9,10,3,2,3,11,0,99,30,40,50";
    final int[] intcodeProgram = reinitializeMemory(input);

    interpret(intcodeProgram);

    assertEquals(3500, intcodeProgram[0]);
  }

  @Test
  void thatExample2Works() {
    String input = "1,0,0,0,99";
    final int[] intcodeProgram = reinitializeMemory(input);

    interpret(intcodeProgram);

    assertEquals(2, intcodeProgram[0]);
  }

  @Test
  void thatExample3Works() {
    String input = "2,3,0,3,99";
    final int[] intcodeProgram = reinitializeMemory(input);

    interpret(intcodeProgram);

    assertEquals(6, intcodeProgram[3]);
  }

  @Test
  void thatExample4Works() {
    String input = "2,4,4,5,99,0";
    final int[] intcodeProgram = reinitializeMemory(input);

    interpret(intcodeProgram);

    assertEquals(9801, intcodeProgram[5]);
  }

  @Test
  void thatExample5Works() {
    String input = "1,1,1,4,99,5,6,0,99";
    final int[] intcodeProgram = reinitializeMemory(input);

    interpret(intcodeProgram);

    assertEquals(30, intcodeProgram[0]);
  }

  @Test
  void thatOpcode3TakesAnInput() {
    String input = "3,0,99";
    final int[] intcodeProgram = reinitializeMemory(input);
    inputQueue.add(30);

    interpret(intcodeProgram);

    assertEquals(30, intcodeProgram[0]);
  }

  @Test
  void thatOpcode4OutputsAValue() {
    String program = "4,2,99";
    final int[] intcodeProgram = reinitializeMemory(program);

    interpret(intcodeProgram);

    assertEquals(99, outputQueue.remove());
  }

  @Test
  void thatDay05InputOutputExampleWorks() {
    String program = "3,0,4,0,99";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(42);

    interpret(intcodeProgram);

    assertEquals(42, outputQueue.remove());
  }

  @Test
  void thatParameterModeWorks() {
    String program = "1002,4,3,4,33";
    final int[] intcodeProgram = reinitializeMemory(program);

    interpret(intcodeProgram);

    assertEquals(99, intcodeProgram[4]);
  }

  @Test
  void thatNegativeIntegersWereTreated() {
    String program = "1101,100,-1,4,0";
    final int[] intcodeProgram = reinitializeMemory(program);

    interpret(intcodeProgram);

    assertEquals(99, intcodeProgram[4]);
  }

  @ParameterizedTest(name = "when {0} is less than 8 the output is {1}")
  @CsvSource({"8,0","7,1"})
  void thatLessThanWorksInPositionMode(int input, int output) {
    String program = "3,9,7,9,10,9,4,9,99,-1,8";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest(name = "when {0} is less than 8 the output is {1}")
  @CsvSource({"8,0","7,1"})
  void thatLessThanWorksInImmediateMode(int input, int output) {
    String program = "3,3,1107,-1,8,3,4,3,99";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"8,1","7,0"})
  void thatEqualsWorksWithPositionMode(int input, int output) {
    String program = "3,9,8,9,10,9,4,9,99,-1,8";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"8,1","7,0"})
  void thatEqualsWorksWithImmediateMode(int input, int output) {
    String program = "3,3,1108,-1,8,3,4,3,99";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"0,0", "42,1"})
  void thatJumpsWorkInPositionMode(int input, int output) {
    String program = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"0,0", "42,1"})
  void thatJumpsWorkInImmediateMode(int input, int output) {
    String program = "3,3,1105,-1,9,1101,0,0,12,4,12,99,1";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @Disabled("For manual execution of the riddle")
  @Test
  void day05Part1() {
    String program = "99";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(1);

    interpret(intcodeProgram);

    LOGGER.info(format("Output queue has %d elements", outputQueue.size()));
    for (Integer output : outputQueue) {
      LOGGER.info(format("Program output: %d", output));
    }
  }

  @Disabled("For manual execution of the riddle")
  @Test
  void day05Part2() {
    String program = "99";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(5);

    interpret(intcodeProgram);

    LOGGER.info(format("Output queue has %d elements", outputQueue.size()));
    for (Integer output : outputQueue) {
      LOGGER.info(format("Program output: %d", output));
    }
  }


  public static void interpret(int... intcodeProgram) {
    int instructionPointer = 0;

    while (true) {
      int opcode = intcodeProgram[instructionPointer];
      int mode3 = 0;
      int mode2 = 0;
      int mode1 = 0;
      if(opcode >= 10_000) {
        mode3 = opcode / 10_000;
        opcode = opcode - mode3 * 10_000;
      }
      if(opcode >= 1_000) {
        mode2 = opcode / 1_000;
        opcode = opcode - mode2 * 1_000;
      }
      if(opcode >= 100) {
        mode1 = opcode / 100;
        opcode = opcode - mode1 * 100;
      }

      switch (opcode) {
        case 1 :
        {
          // add
          int readAddress1 = intcodeProgram[instructionPointer + 1];
          int readAddress2 = intcodeProgram[instructionPointer + 2];
          int writeAddress = intcodeProgram[instructionPointer + 3];
          final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
          final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
          int akku = value1 + value2;
          intcodeProgram[writeAddress] = akku;
          instructionPointer += 4;
          break;
        }
        case 2 :
        {
          // mult
          int readAddress1 = intcodeProgram[instructionPointer + 1];
          int readAddress2 = intcodeProgram[instructionPointer + 2];
          int writeAddress = intcodeProgram[instructionPointer + 3];
          final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
          final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
          int akku = value1 * value2;
          intcodeProgram[writeAddress] = akku;
          instructionPointer += 4;
          break;
        }
        case 3:
        {
          // input
          final Integer value = inputQueue.remove();
          int writeAddress = intcodeProgram[instructionPointer + 1];
          intcodeProgram[writeAddress] = value;
          instructionPointer += 2;
          break;
        }
        case 4:
        {
          // output
          int readAddress1 = intcodeProgram[instructionPointer + 1];
          final int value = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
          outputQueue.add(value);
          instructionPointer += 2;
          break;
        }
        case 5:
        {
          // jump-if-true
          int readAddress1 = intcodeProgram[instructionPointer + 1];
          int readAddress2 = intcodeProgram[instructionPointer + 2];
          final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
          final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
          if(value1 != 0) {
            instructionPointer = value2;
          } else {
            instructionPointer += 3;
          }
          break;
        }
        case 6:
        {
          // jump-if-false
          int readAddress1 = intcodeProgram[instructionPointer + 1];
          int readAddress2 = intcodeProgram[instructionPointer + 2];
          final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
          final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
          if(value1 == 0) {
            instructionPointer = value2;
          } else {
            instructionPointer += 3;
          }
          break;
        }
        case 7:
        {
          // less than
          int readAddress1 = intcodeProgram[instructionPointer + 1];
          int readAddress2 = intcodeProgram[instructionPointer + 2];
          int writeAddress = intcodeProgram[instructionPointer + 3];
          final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
          final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
          int akku = value1 < value2 ? 1 : 0;
          intcodeProgram[writeAddress] = akku;
          instructionPointer += 4;
          break;
        }
        case 8:
        {
          // less than
          int readAddress1 = intcodeProgram[instructionPointer + 1];
          int readAddress2 = intcodeProgram[instructionPointer + 2];
          int writeAddress = intcodeProgram[instructionPointer + 3];
          final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
          final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
          int akku = value1 == value2 ? 1 : 0;
          intcodeProgram[writeAddress] = akku;
          instructionPointer += 4;
          break;
        }
        case 99 :
          // stop
          return;
        default :
          LOGGER.severe("Execution error. Unknown opcode " + opcode);
          return;
      }

    }
  }


  public static int[] reinitializeMemory(String input) {
    int index = 0;
    String[] parts = input.split(",");
    int[] intcodeProgram = new int[parts.length];
    for (String part : parts) {
      intcodeProgram[index] = Integer.valueOf(part);
      index++;
    }
    return intcodeProgram;
  }
}
